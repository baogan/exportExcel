package com.huangxb.job;

import com.huangxb.config.HttpConfig;
import com.huangxb.config.ServerConfig;
import com.huangxb.service.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ReconciliationJob {


  @Autowired
  private HttpConfig httpConfig;
  @Autowired
  private ServerConfig serverConfig;
  private ReconciliationService reconciliationService;

  // set up a schedul job when 3 am clock .
  final static Logger logger = Logger.getLogger(ReconciliationJob.class);
  @Scheduled(cron = "0 30 8 * * ?")
  private void Reconciliation(){
    reconciliationService = new ReconciliationService();
    logger.info("Reconciliation job start.");
    reconciliationService.Reconciliation(httpConfig, serverConfig);
    logger.info("Reconciliation job end.");
  }

}
