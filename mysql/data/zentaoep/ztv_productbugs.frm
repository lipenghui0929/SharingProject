TYPE=VIEW
query=select `zentaoep`.`zt_bug`.`product` AS `product`,count(0) AS `bugs`,sum(if((`zentaoep`.`zt_bug`.`resolution` = \'\'),0,1)) AS `resolutions`,sum(if((`zentaoep`.`zt_bug`.`severity` <= 2),1,0)) AS `seriousBugs` from `zentaoep`.`zt_bug` where (`zentaoep`.`zt_bug`.`deleted` = \'0\') group by `zentaoep`.`zt_bug`.`product`
md5=e425ff8f8d41ef0c3ff89dfcdf4c1eb7
updatable=0
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2019-01-08 07:59:19
create-version=2
source=select `zt_bug`.`product` AS `product`,count(0) AS `bugs`,sum(if((`zt_bug`.`resolution` = \'\'),0,1)) AS `resolutions`,sum(if((`zt_bug`.`severity` <= 2),1,0)) AS `seriousBugs` from `zt_bug` where (`zt_bug`.`deleted` = \'0\') group by `zt_bug`.`product`
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_general_ci
view_body_utf8=select `zentaoep`.`zt_bug`.`product` AS `product`,count(0) AS `bugs`,sum(if((`zentaoep`.`zt_bug`.`resolution` = \'\'),0,1)) AS `resolutions`,sum(if((`zentaoep`.`zt_bug`.`severity` <= 2),1,0)) AS `seriousBugs` from `zentaoep`.`zt_bug` where (`zentaoep`.`zt_bug`.`deleted` = \'0\') group by `zentaoep`.`zt_bug`.`product`
mariadb-version=100036
