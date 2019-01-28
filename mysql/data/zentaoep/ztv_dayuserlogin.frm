TYPE=VIEW
query=select count(0) AS `userlogin`,left(`zentaoep`.`zt_action`.`date`,10) AS `day` from `zentaoep`.`zt_action` where ((`zentaoep`.`zt_action`.`objectType` = \'user\') and (`zentaoep`.`zt_action`.`action` = \'login\')) group by left(`zentaoep`.`zt_action`.`date`,10)
md5=49adcc8ca70291fafe503dba22b2dae1
updatable=0
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2019-01-08 07:59:19
create-version=2
source=select count(*) AS `userlogin`,left(`zt_action`.`date`,10) AS `day` from `zt_action` where ((`zt_action`.`objectType` = \'user\') and (`zt_action`.`action` = \'login\')) group by left(`zt_action`.`date`,10)
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_general_ci
view_body_utf8=select count(0) AS `userlogin`,left(`zentaoep`.`zt_action`.`date`,10) AS `day` from `zentaoep`.`zt_action` where ((`zentaoep`.`zt_action`.`objectType` = \'user\') and (`zentaoep`.`zt_action`.`action` = \'login\')) group by left(`zentaoep`.`zt_action`.`date`,10)
mariadb-version=100036
