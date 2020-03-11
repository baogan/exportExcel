package com.huangxb.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
// this is http config values
@Component
@ConfigurationProperties(prefix = "http")
public class HttpConfig {

  private Integer maxConnPerRoute;
  private Integer maxConnTotal;
  private Integer connectTimeout;
  private Integer socketTimeout;
  @Autowired
  private HttpHeaderConfig httpHeaderConfig;

  public HttpHeaderConfig getHttpHeaderConfig() {
    return httpHeaderConfig;
  }

  public void setHttpHeaderConfig(HttpHeaderConfig httpHeaderConfig) {
    this.httpHeaderConfig = httpHeaderConfig;
  }


  public Integer getMaxConnPerRoute() {
    return maxConnPerRoute;
  }

  public Integer getMaxConnTotal() {
    return maxConnTotal;
  }

  public Integer getConnectTimeout() {
    return connectTimeout;
  }

  public Integer getSocketTimeout() {
    return socketTimeout;
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

  @Override
  public String toString() {
    return "HttpConfig{" +
        "maxConnPerRoute=" + maxConnPerRoute +
        ", maxConnTotal=" + maxConnTotal +
        ", connectTimeout=" + connectTimeout +
        ", socketTimeout=" + socketTimeout +
        ", httpHeaderConfig=" + httpHeaderConfig +
        '}';
  }
}
