package com.huangxb.util;


import java.util.List;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {


  private HttpUtils httpUtils;

  @Test
  public void  UtilTest() {
    List<NameValuePair> params = new ArrayList<NameValuePair>();

    ///httpUtils = HttpUtils.create();
//    try {
//      String respon = httpUtils.doPost(params);
//      System.out.println(respon);
//    } catch (Exception e) {
//      e.printStackTrace();
//      System.out.println("exception!!!");
//    }

  }
}
