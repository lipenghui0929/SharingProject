TYPE=VIEW
query=select `zentaoep`.`zt_team`.`root` AS `project`,count(\'*\') AS `teams` from `zentaoep`.`zt_team` where (`zentaoep`.`zt_team`.`type` = \'project\') group by `zentaoep`.`zt_team`.`root`
md5=d7203da9d6bbaab201b3c62777d0e48a
updatable=0
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2019-01-08 07:59:19
create-version=2
source=select `zt_team`.`root` AS `project`,count(\'*\') AS `teams` from `zt_team` where `type` = \'project\' group by `zt_team`.`root`
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_general_ci
view_body_utf8=select `zentaoep`.`zt_team`.`root` AS `project`,count(\'*\') AS `teams` from `zentaoep`.`zt_team` where (`zentaoep`.`zt_team`.`type` = \'project\') group by `zentaoep`.`zt_team`.`root`
mariadb-version=100036
