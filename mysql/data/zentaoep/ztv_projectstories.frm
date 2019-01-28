TYPE=VIEW
query=select `t1`.`project` AS `project`,count(\'*\') AS `stories`,sum(if((`t2`.`status` = \'closed\'),0,1)) AS `undone` from (`zentaoep`.`zt_projectstory` `t1` left join `zentaoep`.`zt_story` `t2` on((`t1`.`story` = `t2`.`id`))) group by `t1`.`project`
md5=c842e0f87e3bf9ad5be0d66f66aea7e0
updatable=0
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2019-01-08 07:59:19
create-version=2
source=select `t1`.`project` AS `project`,count(\'*\') AS `stories`,sum(if((`t2`.`status` = \'closed\'),0,1)) AS `undone` from (`zt_projectstory` `t1` left join `zt_story` `t2` on((`t1`.`story` = `t2`.`id`))) group by `t1`.`project`
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_general_ci
view_body_utf8=select `t1`.`project` AS `project`,count(\'*\') AS `stories`,sum(if((`t2`.`status` = \'closed\'),0,1)) AS `undone` from (`zentaoep`.`zt_projectstory` `t1` left join `zentaoep`.`zt_story` `t2` on((`t1`.`story` = `t2`.`id`))) group by `t1`.`project`
mariadb-version=100036
