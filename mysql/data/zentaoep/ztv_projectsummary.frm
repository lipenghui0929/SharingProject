TYPE=VIEW
query=select `zentaoep`.`zt_task`.`project` AS `project`,sum(`zentaoep`.`zt_task`.`estimate`) AS `estimate`,sum(`zentaoep`.`zt_task`.`consumed`) AS `consumed`,sum(if(((`zentaoep`.`zt_task`.`status` <> \'cancel\') and (`zentaoep`.`zt_task`.`status` <> \'closed\')),`zentaoep`.`zt_task`.`left`,0)) AS `left`,count(0) AS `number`,sum(if(((`zentaoep`.`zt_task`.`status` = \'wait\') or (`zentaoep`.`zt_task`.`status` = \'doing\')),1,0)) AS `undone`,sum((`zentaoep`.`zt_task`.`consumed` + if((`zentaoep`.`zt_task`.`status` <> \'cancel\'),`zentaoep`.`zt_task`.`left`,0))) AS `totalReal` from `zentaoep`.`zt_task` where ((`zentaoep`.`zt_task`.`deleted` = \'0\') and (`zentaoep`.`zt_task`.`parent` >= \'0\')) group by `zentaoep`.`zt_task`.`project`
md5=adf6b861e2c72d5b96d472361076352f
updatable=0
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2019-01-08 07:59:19
create-version=2
source=select `zt_task`.`project` AS `project`,sum(`zt_task`.`estimate`) AS `estimate`,sum(`zt_task`.`consumed`) AS `consumed`,sum(if(`zt_task`.status != \'cancel\' and `zt_task`.status != \'closed\', `left`, 0)) AS `left`,count(0) AS `number`,sum(if(((`zt_task`.`status` = \'wait\') or (`zt_task`.`status` = \'doing\')),1,0)) AS `undone`,sum((`zt_task`.`consumed` + if(`zt_task`.status != \'cancel\', `left`, 0))) AS `totalReal` from `zt_task` where ((`zt_task`.`deleted` = \'0\') and (`zt_task`.`parent` >= \'0\')) group by `zt_task`.`project`
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_general_ci
view_body_utf8=select `zentaoep`.`zt_task`.`project` AS `project`,sum(`zentaoep`.`zt_task`.`estimate`) AS `estimate`,sum(`zentaoep`.`zt_task`.`consumed`) AS `consumed`,sum(if(((`zentaoep`.`zt_task`.`status` <> \'cancel\') and (`zentaoep`.`zt_task`.`status` <> \'closed\')),`zentaoep`.`zt_task`.`left`,0)) AS `left`,count(0) AS `number`,sum(if(((`zentaoep`.`zt_task`.`status` = \'wait\') or (`zentaoep`.`zt_task`.`status` = \'doing\')),1,0)) AS `undone`,sum((`zentaoep`.`zt_task`.`consumed` + if((`zentaoep`.`zt_task`.`status` <> \'cancel\'),`zentaoep`.`zt_task`.`left`,0))) AS `totalReal` from `zentaoep`.`zt_task` where ((`zentaoep`.`zt_task`.`deleted` = \'0\') and (`zentaoep`.`zt_task`.`parent` >= \'0\')) group by `zentaoep`.`zt_task`.`project`
mariadb-version=100036
