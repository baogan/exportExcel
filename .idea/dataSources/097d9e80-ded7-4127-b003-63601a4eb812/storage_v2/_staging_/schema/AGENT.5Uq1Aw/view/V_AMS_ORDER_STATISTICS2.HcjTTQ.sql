create view V_AMS_ORDER_STATISTICS2 as
select `A1`.`MCHT_ID`                                                                             AS `mcht_id`,
       `A2`.`MCHT_NAME`                                                                           AS `mcht_name`,
       substr(`A1`.`APP_TIME`, 1, 8)                                                              AS `trade_date`,
       (case
            when (`A1`.`RETURN_STATUS` in ('0', '10086', '00', '000', '001')) then 1
            else 0 end)                                                                           AS `dispose_count`,
       (case
            when (`A1`.`RETURN_STATUS` in ('1', 'S', '03', '3', '2', '120')) then 1
            else 0 end)                                                                           AS `success_count`,
       `A1`.`AMOUNT_PAID`                                                                         AS `success_sum`
from (`AGENT`.`AMS_OPR_ORDER_RECORD` `A1`
         left join `AGENT`.`AMS_MCHT_BASE_INF` `A2` on ((`A1`.`MCHT_ID` = `A2`.`MCHT_ID`)))
where (`A1`.`APP_TIME` >= charset(date_format(now(),'%Y%m%d')));

