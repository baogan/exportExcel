package com.huangxb.service;

import com.huangxb.config.HttpConfig;
import com.huangxb.config.ServerConfig;
import com.huangxb.util.BufferWrite;
import com.huangxb.util.HttpUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.DigestUtils;
import org.apache.log4j.Logger;


public class ReconciliationService {

  final static Logger logger = Logger.getLogger(ReconciliationService.class);
  public List<NameValuePair> PrepareData(ServerConfig serverConfig) {
    String result = "";
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
   // get the day before the current calendar date
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -1);
    logger.info("Swt checkout date :" + simpleDateFormat.format(calendar.getTime()));
    params.add(new BasicNameValuePair("billDate", simpleDateFormat.format(calendar.getTime())));
//     params.add(new BasicNameValuePair("billDate", "20200216"));
    params.add(new BasicNameValuePair("merchantId", serverConfig.getMerchantId()));
    params.add(new BasicNameValuePair("signType", serverConfig.getSignType()));
    params.add(new BasicNameValuePair("version", serverConfig.getVersion()));

    for (NameValuePair param : params) {
      result += param.toString();
      result += "&";
    }
    result = result.substring(0, result.length() - 1);
    result += serverConfig.getMD5Key();
    // md5 digest
    String signature = DigestUtils.md5DigestAsHex(result.getBytes());
    params.add(new BasicNameValuePair("signature", signature));
    logger.info("Swt Request param: "+params);
    return params;
  }
// response text/xml format to parse
  public Map<String, String> ResponseXmlParse(String response) {
    Map<String, String> ResponseMap = new HashMap<>();
    String retCode;
    String billDate;
    Integer index = response.indexOf("retCode=");
    retCode = response.substring(index + 9, index + 13);
    // return code
    ResponseMap.put("retCode", retCode);
    index = response.indexOf("billDate=");
    billDate = response.substring(index + 10, index + 18);
    // bill date to query
    ResponseMap.put("billDate", billDate);
    index = response.indexOf("filecontent=");
    // file content we need
    String fileContent = response.substring(index + 13, response.length() - 3);

    // base64 decode the bytes of file content
    byte[] byteContent = Base64.getDecoder().decode(fileContent.getBytes());
    String decodeContent = new String(byteContent);
    ResponseMap.put("filecontent", decodeContent);
    logger.info("response message: retCode=" + retCode+" billDate="+billDate+"size of fileContent="+decodeContent.length());
    return ResponseMap;
  }

  public void Reconciliation(HttpConfig httpConfig, ServerConfig serverConfig) {

    HttpUtils httpUtils = HttpUtils.create().build(httpConfig, serverConfig);
    try {

      Map<String,String> ResponseMap = ResponseXmlParse(httpUtils.doPost(PrepareData(serverConfig)));
      // to write a swt*.txt file for file content
      BufferWrite.UseBufferedWriter(ResponseMap.get("filecontent"), serverConfig.getFilePath()+"swt-"+ResponseMap.get("billDate")+".txt");
      logger.info("Write txt file at :" + serverConfig.getFilePath()+"swt-"+ResponseMap.get("billDate")+".txt");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("exception!!!");
    }


  }

}
