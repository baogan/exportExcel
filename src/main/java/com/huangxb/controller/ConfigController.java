package com.huangxb.controller;


import com.huangxb.config.HttpConfig;
import com.huangxb.config.ServerConfig;
import com.huangxb.service.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class ConfigController {


  @Autowired
  private ServerConfig serverConfig;
  @Autowired
  private HttpConfig httpConfig;

  @RequestMapping("/UtilTest")
  public void UtilTest() {
    ReconciliationService reconciliationService = new ReconciliationService();
    reconciliationService.Reconciliation(httpConfig, serverConfig);

  }


}
