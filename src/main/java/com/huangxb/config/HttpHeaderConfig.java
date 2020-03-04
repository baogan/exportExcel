package com.huangxb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "http.header")
public class HttpHeaderConfig {

  private String ContentType;
  private String Accept;
  private String Charset;

  public String getContentType() {
    return ContentType;
  }

  public void setContentType(String contentType) {
    ContentType = contentType;
  }

  public String getAccept() {
    return Accept;
  }

  public void setAccept(String accept) {
    Accept = accept;
  }

  public String getCharset() {
    return Charset;
  }

  public void setCharset(String charset) {
    Charset = charset;
  }

  @Override
  public String toString() {
    return "HttpHeaderConfig{" +
        "ContentTye='" + ContentType + '\'' +
        ", Accept='" + Accept + '\'' +
        ", Charset='" + Charset + '\'' +
        '}';
  }
}
