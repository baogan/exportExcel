package com.huangxb.util;


import com.huangxb.config.HttpConfig;
import com.huangxb.config.ServerConfig;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;



public class HttpUtils {


  private Integer maxConnPerRoute;
  private Integer maxConnTotal;
  private Integer connectTimeout;
  private Integer socketTimeout;
  private String uri;

  private String ContentType;
  private String Charset;

  private CloseableHttpClient closeableHttpClient;

  protected HttpUtils(){
    super();
    this.maxConnPerRoute = 0;
    this.maxConnTotal = 0;
    this.connectTimeout = -1;
    this.socketTimeout = -1;
  }


  public static HttpUtils create(){
    return new HttpUtils();
  }
  public HttpUtils build(HttpConfig httpConfig, ServerConfig serverConfig){

    HttpClientBuilder builder = HttpClientBuilder.create();
    if(httpConfig.getMaxConnPerRoute() != null && httpConfig.getMaxConnPerRoute() > 0)
      builder.setMaxConnPerRoute(maxConnPerRoute=httpConfig.getMaxConnPerRoute());
    if(httpConfig.getMaxConnTotal() != null && httpConfig.getMaxConnTotal() > 0)
      builder.setMaxConnTotal(maxConnTotal=httpConfig.getMaxConnPerRoute());

    RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
    if(httpConfig.getConnectTimeout() != null && httpConfig.getConnectTimeout() > 0)
      requestConfigBuilder.setConnectionRequestTimeout(connectTimeout=httpConfig.getConnectTimeout());
    if(httpConfig.getSocketTimeout() != null && httpConfig.getSocketTimeout() > 0)
      requestConfigBuilder.setSocketTimeout(socketTimeout=httpConfig.getSocketTimeout());

    if(serverConfig.getUri() != null)
      this.uri = serverConfig.getUri();

    this.ContentType = httpConfig.getHttpHeaderConfig().getContentType() != null ? httpConfig.getHttpHeaderConfig()
        .getContentType():"application/x-www-form-urlencoded";
    this.Charset = httpConfig.getHttpHeaderConfig().getCharset() != null ? httpConfig.getHttpHeaderConfig()
        .getCharset():"UTF-8";

    builder.setDefaultRequestConfig(requestConfigBuilder.build());
    closeableHttpClient = builder.build();
    return this;
  }

  public String doPost(List<NameValuePair> formparam)throws Exception{
    String ResponString = null;
    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparam,Charset);
    HttpPost httpPost = new HttpPost(uri);
    httpPost.addHeader(HttpHeaders.CONTENT_TYPE,ContentType);
    httpPost.setEntity(urlEncodedFormEntity);
    CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
    try{
      HttpEntity httpEntity = closeableHttpResponse.getEntity();
      if(httpEntity != null){
          long len = httpEntity.getContentLength();

          // do something useful
          System.out.println("POST request return length: " +len+" code = "+closeableHttpResponse.getStatusLine().getStatusCode());
          if(closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            ResponString = EntityUtils.toString(httpEntity,Charset);
            return ResponString;
          }else{
            return null;
          }

      }else{
        System.out.println("POST http status: " + closeableHttpResponse.getStatusLine() +" code= "
            +closeableHttpResponse.getStatusLine().getStatusCode());
        return null;
      }

    }finally {
      closeableHttpResponse.close();
    }


  }

  public void setMaxConnPerRoute(Integer maxConnPerRoute) {
    this.maxConnPerRoute = maxConnPerRoute;
  }

  public void setMaxConnTotal(Integer maxConnTotal) {
    this.maxConnTotal = maxConnTotal;
  }

  public void setConnectTimeout(Integer connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public void setSocketTimeout(Integer socketTimeout) {
    this.socketTimeout = socketTimeout;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public void setContentType(String contentType) {
    this.ContentType = contentType;
  }


  public void setCharset(String charset) {
    this.Charset = charset;
  }


}
