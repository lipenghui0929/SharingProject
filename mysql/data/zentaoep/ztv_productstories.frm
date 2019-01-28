TYPE=VIEW
query=select `zentaoep`.`zt_story`.`product` AS `product`,count(\'*\') AS `stories`,sum(if((`zentaoep`.`zt_story`.`status` = \'closed\'),0,1)) AS `undone` from `zentaoep`.`zt_story` where (`zentaoep`.`zt_story`.`deleted` = \'0\') group by `zentaoep`.`zt_story`.`product`
md5=00b95c75984d77cd2a6eb86c53651845
updatable=0
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2019-01-08 07:59:19
create-version=2
source=select `zt_story`.`product` AS `product`,count(\'*\') AS `stories`,sum(if((`zt_story`.`status` = \'closed\'),0,1)) AS `undone` from `zt_story` where (`zt_story`.`deleted` = \'0\') group by `zt_story`.`product`
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_general_ci
view_body_utf8=select `zentaoep`.`zt_story`.`product` AS `product`,count(\'*\') AS `stories`,sum(if((`zentaoep`.`zt_story`.`status` = \'closed\'),0,1)) AS `undone` from `zentaoep`.`zt_story` where (`zentaoep`.`zt_story`.`deleted` = \'0\') group by `zentaoep`.`zt_story`.`product`
mariadb-version=100036
