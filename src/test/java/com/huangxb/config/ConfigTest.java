package com.huangxb.config;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigTest {

  @Autowired
  private HttpConfig httpConfig;


// get a calendar date before the current day
  @Test
  public void contextLoads() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    System.out.println(httpConfig.toString());
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -1);
    String date = simpleDateFormat.format(calendar.getTime());
    System.out.println(calendar.getTimeZone());
  }
}
