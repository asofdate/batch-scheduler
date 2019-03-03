package com.asofdate.hauth.sql.db;

import com.asofdate.hauth.sql.SQLFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

/**
 * Created by hzwy23 on 2017/7/21.
 */
@Configuration
public class MySqlDefine implements SQLFactory {
    private final String sys001 = "select user_id,user_passwd,status_id,continue_error_cnt from sys_sec_user where user_id = ?";
    private final String sys002 = "select t.domain_id from sys_org_info t where t.org_unit_id  = ?";
    private final String sys003 = "select i.domain_id from sys_user_info t inner join sys_org_info i on t.org_unit_id = i.org_unit_id where t.user_id = ?";
    private final String sys004 = "select domain_id from sys_role_info where role_id = ?";
    private final String sys005 = "update sys_resource_info set res_name = ?, res_up_id = ? where res_id = ?";
    private final String sys006 = "select count(*) from sys_theme_value where theme_id = ? and res_id = ?";
    private final String sys007 = "delete from sys_user_info where user_id = ? and org_unit_id = ?";
    private final String sys008 = "insert into sys_theme_value(uuid,theme_id,res_id,res_url,res_type,res_bg_color,res_class,group_id,res_img,sort_id,new_iframe) value(replace(uuid(),'-',''),?,?,?,?,?,?,?,?,?,?)";
    private final String sys009 = "update sys_theme_value set res_url = ?, res_bg_color = ?, res_class = ?, res_img = ?, group_id = ?, sort_id = ?, res_type = ?, new_iframe = ? where theme_id = ? and res_id = ?";
    //    private final String sys010 = "select authorization_level from sys_domain_share_info where domain_id = ? and target_domain_id = ?";
    private final String sys011 = "select distinct t2.res_url from sys_user_theme t1 inner join sys_theme_value t2 on t1.theme_id = t2.theme_id inner join sys_resource_info t3 on t2.res_id = t3.res_id where t1.user_id = ? and t2.res_id = ? and t3.res_type = '0'";
    private final String sys012 = "select uuid,user_id,handle_time,client_ip,status_code,method,url,data from sys_handle_logs t order by handle_time desc";
    private final String sys013 = "select res_type from sys_resource_info where res_id = ?";
    private final String sys014 = "update sys_sec_user set user_passwd = ? where user_id = ? and user_passwd = ?";
    private final String sys015 = "update sys_sec_user set user_passwd = ? where user_id = ?";
    private final String sys016 = "update sys_sec_user set status_id = ? ,continue_error_cnt = '0' where user_id = ?";
    private final String sys017 = "select t.user_id,t.user_name,a.status_desc,t.user_create_date as create_date, t.user_owner as create_user,t.user_email,t.user_phone,i.org_unit_id,i.org_unit_desc, t.user_maintance_date as modify_date,t.user_maintance_user as modify_user,u.status_id as status_cd from sys_user_info t inner join sys_sec_user u on t.user_id = u.user_id inner join sys_user_status_attr a on u.status_id = a.status_id inner join sys_org_info i on i.org_unit_id = t.org_unit_id";
    private final String sys018 = "insert into sys_user_info (user_id,user_name,user_create_date,user_owner,user_email,user_phone,org_unit_id,user_maintance_date,user_maintance_user) values(?,?,now(),?,?,?,?,now(),?)";
    private final String sys019 = "insert into sys_sec_user(user_id,user_passwd,status_id) values(?,?,?)";
    private final String sys020 = "update sys_sec_user set user_passwd = ? where user_id = ?";
    private final String sys021 = "update sys_user_info t set t.user_name = ?, t.user_phone = ?, t.user_email = ? ,t.user_maintance_date = now(), t.user_maintance_user = ?,t.org_unit_id = ? where t.user_id = ?";
    private final String sys022 = "select count(*) from sys_role_user r inner join sys_role_resource e on r.role_id = e.role_id inner join sys_theme_value v on e.res_id = v.res_id inner join sys_user_theme m on v.theme_id = m.theme_id and r.user_id = m.user_id where r.user_id = ? and v.res_url = ?";
    private final String sys023 = "select t.user_id,t.user_name,a.status_desc,t.user_create_date, t.user_owner,t.user_email,t.user_phone,i.org_unit_id,i.org_unit_desc, t.user_maintance_date,t.user_maintance_user,u.status_id from sys_user_info t inner join sys_sec_user u on t.user_id = u.user_id inner join sys_user_status_attr a on u.status_id = a.status_id inner join sys_org_info i on i.org_unit_id = t.org_unit_id where t.user_id = ?";
    private final String sys024 = "update sys_user_theme set theme_id = ? where user_id = ?";
    private final String sys025 = "select t.domain_id, t.domain_name as domain_desc, s.domain_status_name  as domain_status_desc, t.domain_create_date as maintance_date , t.domain_owner as create_user_id,t.domain_maintance_date as domain_modify_date,t.domain_maintance_user as domain_modify_user,t.domain_status_id as domain_status_cd from sys_domain_info t inner join sys_domain_status_attr s on t.domain_status_id = s.domain_status_id";
    private final String sys026 = "insert into sys_role_info(role_id,role_name,role_owner,role_create_date,role_status_id,role_maintance_date,role_maintance_user,code_number) values(?,?,?,now(),?,now(),?,?)";
    private final String sys027 = "delete from sys_role_info where role_id = ?";
    private final String sys028 = "select t.code_number,t.role_name,t.role_owner as create_user,t.role_create_date as create_date,a.role_status_desc,a.role_status_id as role_status_code, t.role_maintance_date as modify_date,t.role_maintance_user as modify_user,t.role_id from sys_role_info t inner join sys_role_status_attr a on t.role_status_id = a.role_status_id";
    private final String sys029 = "select uuid,user_id,handle_time,client_ip,status_code,method,url,data from sys_handle_logs t order by handle_time desc limit ?,?";
    private final String sys030 = "select count(*) from sys_handle_logs t";
    private final String sys031 = "select uuid,user_id,handle_time,client_ip,status_code,method,url,data from sys_handle_logs t where t.domain_id = ? and user_id = ? and handle_time >= str_to_date(?,'%Y-%m-%d') and handle_time < str_to_date(?,'%Y-%m-%d') order by handle_time desc";
    private final String sys032 = "select uuid,user_id,handle_time,client_ip,status_code,method,url,data from sys_handle_logs t where t.domain_id = ? and user_id = ? and handle_time >= str_to_date(?,'%Y-%m-%d') order by handle_time desc";
    private final String sys033 = "select uuid,user_id,handle_time,client_ip,status_code,method,url,data from sys_handle_logs t where t.domain_id = ? and handle_time >= str_to_date(?,'%Y-%m-%d') and handle_time < str_to_date(?,'%Y-%m-%d') order by handle_time desc";
    private final String sys034 = "";
    private final String sys035 = "select uuid,user_id,handle_time,client_ip,status_code,method,url,data from sys_handle_logs t where t.domain_id = ? and handle_time >= str_to_date(?,'%Y-%m-%d') order by handle_time desc";
    private final String sys036 = "insert into sys_domain_info(domain_id,domain_name,domain_status_id,domain_create_date,domain_owner,domain_maintance_date,domain_maintance_user) values(?,?,?,now(),?,now(),?)";
    private final String sys037 = "delete from sys_domain_info where domain_id = ?";
    private final String sys038 = "update sys_domain_info set domain_name = ?, domain_status_id = ?, domain_maintance_date = now(), domain_maintance_user = ? where domain_id = ?";
    private final String sys039 = "select uuid,user_id,handle_time,client_ip,status_code,method,url,data from sys_handle_logs t where t.domain_id = ? and handle_time < str_to_date(?,'%Y-%m-%d') order by handle_time desc";
    private final String sys040 = "select uuid,user_id,handle_time,client_ip,status_code,method,url,data from sys_handle_logs t where t.domain_id = ? and user_id = ? order by handle_time desc";
    private final String sys041 = "select org_unit_id as org_id,org_unit_desc as org_desc,up_org_id, create_date,maintance_date as modify_date,create_user,maintance_user as modify_user,code_number from sys_org_info t";
    private final String sys042 = "select uuid,user_id,handle_time,client_ip,status_code,method,url,data from sys_handle_logs t where t.domain_id = ? order by user_id,handle_time desc";
    private final String sys043 = "insert into sys_org_info(code_number,org_unit_desc,up_org_id,create_date,maintance_date,create_user,maintance_user,org_unit_id) values(?,?,?,now(),now(),?,?,?)";
    private final String sys044 = "delete from sys_org_info where org_unit_id = ? and domain_id = ?";
    private final String sys045 = "insert into sys_user_theme(user_id,theme_id) values(?,?)";
    private final String sys046 = "select t.role_id,t.role_name,t.code_number from sys_role_info t where ( t.role_owner = ? or exists ( select 1 from sys_role_user r where r.user_id = ? and t.role_id = r.role_id ))";
//    private final String sys047 = "";
    private final String sys048 = "insert into sys_role_user(uuid,role_id,user_id,maintance_date,maintance_user) values(replace(uuid(),'-',''),?,?,now(),?)";
    private final String sys050 = "update sys_role_info t set t.role_name = ? ,t.role_status_id = ?, role_maintance_date = now(), role_maintance_user = ? where t.role_id = ?";
    private final String sys069 = "update sys_org_info set org_unit_desc = ?, up_org_id = ?, maintance_date = now(), maintance_user= ? where org_unit_id = ?";
    private final String sys070 = "select t.theme_id,i.theme_desc, res_id,res_url,res_type,res_bg_color,res_class, t.group_id, t.res_img, t.sort_id, t.new_iframe from sys_theme_value t left join sys_theme_info i on t.theme_id = i.theme_id where t.theme_id = ? and t.res_id = ?";
    private final String sys071 = "select t.res_id,t.res_name,t.res_attr, a.res_attr_desc,t.res_up_id,t.res_type,r.res_type_desc,t.sys_flag from sys_resource_info t inner join sys_resource_info_attr a on t.res_attr = a.res_attr inner join sys_resource_type_attr r on t.res_type = r.res_type";
    private final String sys072 = "insert into sys_resource_info(res_id,res_name,res_attr,res_up_id,res_type) values(?,?,?,?,?)";
    private final String sys073 = "select t.id,d.up_id from dispatch_batch_group_relation t inner join dispatch_group_dependency d on t.id = d.id where t.batch_id = ?";
    private final String sys074 = "insert into sys_role_resource(uuid,role_id,res_id) values(?,?,?)";
    private final String sys075 = "select t.id, d.up_id from dispatch_group_task_relation t inner join  dispatch_task_dependency d on t.id = d.id where t.group_id = ?";
    private final String sys076 = "delete from sys_theme_value where res_id = ?";
    private final String sys077 = "delete from sys_resource_info where res_id = ? and sys_flag is null";
    private final String sys078 = "select t1.res_url from sys_index_page t1 inner join sys_user_theme t2 on t1.theme_id = t2.theme_id where t2.user_id = ?";
    private final String sys079 = "select distinct domain_id from sys_user_info i inner join sys_org_info o on i.org_unit_id = o.org_unit_id where user_id = ?";
    private final String sys080 = "select o.org_unit_id from sys_user_info i inner join sys_org_info o on i.org_unit_id = o.org_unit_id where user_id = ?";
    //    private final String sys083 = "select t.uuid,t.target_domain_id,i.domain_name,t.authorization_level,t.create_user,t.create_date,t.modify_user,t.modify_date from sys_domain_share_info t inner join sys_domain_info i on t.target_domain_id = i.domain_id where t.domain_id = ?";
    private final String sys084 = "select t.domain_id, t.domain_name as domain_desc, s.domain_status_name  as domain_status_desc, t.domain_create_date  as maintance_date, t.domain_owner as create_user_id,t.domain_maintance_date as domain_modify_date,t.domain_maintance_user as domain_modify_user from sys_domain_info t inner join sys_domain_status_attr s  on t.domain_status_id = s.domain_status_id where t.domain_id = ?";
    //    private final String sys085 = "select t.domain_id, t.domain_name from sys_domain_info t where not exists ( select 1 from sys_domain_share_info i where t.domain_id = i.target_domain_id and i.domain_id = ? ) and t.domain_id <> ?";
//    private final String sys086 = "insert into sys_domain_share_info(uuid,domain_id,target_domain_id,authorization_level,create_user,create_date,modify_date,modify_user) values(uuid(),?,?,?,?,now(),now(),?)";
//    private final String sys087 = "delete from sys_domain_share_info where uuid = ?";
//    private final String sys088 = "update sys_domain_share_info set authorization_level = ?,modify_user = ? , modify_date = now() where uuid = ?";
    private final String sys089 = "select t.res_id,t.res_name,t.res_attr, a.res_attr_desc,t.res_up_id,t.res_type,r.res_type_desc from sys_resource_info t inner join sys_resource_info_attr a on t.res_attr = a.res_attr inner join sys_resource_type_attr r on t.res_type = r.res_type where res_id = ?";
    private final String sys093 = "delete from sys_role_resource where role_id = ? and res_id = ?";
    private final String sys094 = "select r.user_id, t.role_id, t.code_number,t.role_name,t.role_status_id from sys_role_info t inner join sys_role_user r on t.role_id = r.role_id where r.user_id = ? and t.role_status_id = '0'";
    private final String sys095 = "select t.role_id, t.code_number, t.role_name from sys_user_info i inner join sys_org_info o on i.org_unit_id = o.org_unit_id inner join sys_role_info t where i.user_id = ? and t.role_status_id = '0' and  not exists ( select 1 from sys_role_user r where i.user_id = r.user_id and r.role_id = t.role_id )";
    private final String sys096 = "insert into sys_role_user(uuid,role_id,user_id,maintance_date,maintance_user) values(?,?,?,now(),?)";
    private final String sys097 = "delete from sys_role_user where uuid = ?";
    private final String sys098 = "update sys_sec_user set continue_error_cnt = ? where user_id = ?";
    private final String sys099 = "update sys_sec_user set status_id = 1 where user_id = ?";
    private final String sys100 = "select role_id,res_id from sys_role_resource where role_id = ?";
    private final String sys101 = "select t.theme_id,i.theme_desc,res_id,res_url,res_type,res_bg_color,res_class,group_id,res_img,sort_id,new_iframe from sys_theme_value t inner join sys_theme_info i on t.theme_id = i.theme_id where t.theme_id = ? order by group_id,sort_id asc";
    private final String sys102 = "select as_of_date from dispatch_batch_define where batch_id = ? and complete_date >= as_of_date";
    private final String sys103 = "select theme_id from sys_user_theme where user_id = ?";
    private final String sys104 = "select t.arg_id, t.arg_type,a.arg_type_desc,t.arg_value,t.code_number,t.create_user,t.create_date,t.modify_user,t.modify_date,t.domain_id,t.arg_desc,t.bind_as_of_date from dispatch_argument_define t left join dispatch_argument_type_attr a on t.arg_type = a.arg_type where t.domain_id = ?";
    private final String sys105 = "select t.uuid,t.batch_id,t.arg_id,t.arg_value,t.domain_id from dispatch_batch_argument_rel t where t.domain_id = ?";
    private final String sys106 = "select t.id as uuid,t.batch_id,t.group_id,t.domain_id from dispatch_batch_group_relation t where t.domain_id = ?";
    private final String sys107 = "select t.batch_id,t.code_number,t.batch_desc,t.batch_status, t.as_of_date,t.start_date,t.ret_msg, t.complete_date, t.end_date,t.domain_id,a.batch_status_desc from dispatch_batch_define t inner join dispatch_batch_status_attr a on t.batch_status = a.batch_status where t.domain_id = ?";
    private final String sys108 = "select t.group_id,t.code_number,t.group_desc,t.create_user,t.create_date,t.modify_user,t.modify_date,t.domain_id from dispatch_group_define t where t.domain_id = ?";
    private final String sys109 = "select t.id as uuid,t.group_id,t.task_id,t.domain_id from dispatch_group_task_relation t where t.domain_id = ?";
    private final String sys110 = "select t.uuid,t.task_id,t.arg_id,t.arg_value,t.domain_id,t.sort_id from dispatch_task_argument_rel t where t.domain_id = ?";
    private final String sys111 = "select t.task_id,t.code_number,t.task_desc,t.task_type,a.task_type_desc,t.create_user,t.create_date,t.modify_date,t.modify_user,t.domain_id,t.script_file from dispatch_task_define t inner join dispatch_task_type_attr a on t.task_type = a.task_type where t.domain_id = ?";
    private final String sys112 = "select uuid,id,up_id,domain_id from dispatch_group_dependency where domain_id = ?";
    private final String sys113 = "select uuid,id,up_id,domain_id from dispatch_task_dependency where domain_id = ?";
    private final String sys114 = "select uuid,id,arg_id,arg_value,domain_id from dispatch_group_argument_rel where domain_id = ?";
//    private final String sys115 = "select distinct r.res_id from sys_role_user t inner join sys_role_resource r on t.role_id = r.role_id where t.user_id = ?";
    private final String sys116 = "select uuid,domain_id,user_id,authorization_level from sys_domain_authorization where user_id = ?";
    //    private final String sys117 = "select uuid,domain_id,target_domain_id,authorization_level from sys_domain_share_info where domain_id = ?";
    private final String sys118 = "select t.domain_id,t.domain_name as domain_desc,t.domain_status_id,a.domain_status_name as domain_status_desc from sys_domain_info t inner join sys_domain_status_attr a on t.domain_status_id = a.domain_status_id";
    private final String sys119 = "insert into dispatch_argument_define(arg_id,arg_type,arg_value,code_number,create_user,create_date,modify_user,modify_date,domain_id,arg_desc,bind_as_of_date) values(?,?,?,?,?,now(),?,now(),?,?,?)";
    private final String sys120 = "delete from dispatch_argument_define where arg_id = ? and domain_id = ?";
    private final String sys121 = "update dispatch_argument_define set modify_user = ?, modify_date = now(), bind_as_of_date = ?, arg_desc = ?, arg_value = ? where arg_id = ? and domain_id = ?";
    private final String sys122 = "delete from dispatch_group_define where group_id = ? and domain_id = ?";
    private final String sys123 = "update dispatch_group_define set group_desc = ? where group_id = ? and domain_id = ?";
    private final String sys124 = "insert into dispatch_group_define(group_id,code_number,group_desc,create_user,create_date,modify_user,modify_date,domain_id) values(?,?,?,?,now(),?,now(),?)";
    private final String sys125 = "insert into dispatch_task_define(task_id,code_number,task_desc,task_type,create_user,create_date,modify_date,modify_user,domain_id,script_file) values(?,?,?,?,?,now(),now(),?,?,?)";
    private final String sys126 = "update dispatch_task_define set task_desc = ?, task_type = ?,script_file = ? where task_id = ? and domain_id = ?";
    private final String sys127 = "delete from dispatch_task_define where task_id = ? and domain_id = ?";
    private final String sys128 = "insert into dispatch_batch_define(batch_id,code_number,batch_desc,batch_status,as_of_date,ret_msg,complete_date,domain_id) values(?,?,?,?,str_to_date(?,'%Y-%m-%d'),?,str_to_date(?,'%Y-%m-%d'),?)";
    private final String sys129 = "delete from dispatch_batch_define where batch_id = ? and domain_id = ?";
    private final String sys130 = "update dispatch_batch_define set batch_desc = ?, batch_status = ?,as_of_date = str_to_date(?,'%Y-%m-%d'), complete_date = str_to_date(?,'%Y-%m-%d') where batch_id = ? and domain_id = ?";
    private final String sys131 = "select batch_status from dispatch_batch_define where batch_id = ?";
    private final String sys132 = "select t.uuid,t.task_id,t.arg_id,t.arg_value,t.sort_id,t.domain_id,d.arg_type,a.arg_type_desc,d.arg_desc,d.code_number,d.arg_value as fixed_arg_value from dispatch_task_argument_rel t inner join dispatch_argument_define d on t.arg_id = d.arg_id inner join dispatch_argument_type_attr a on d.arg_type = a.arg_type where t.task_id = ? order by sort_id asc";
    private final String sys133 = "select t.id,t.group_id,t.task_id,t.domain_id,d.task_desc,d.task_type,a.task_type_desc,d.code_number from dispatch_group_task_relation t inner join dispatch_task_define d on t.task_id = d.task_id inner join dispatch_task_type_attr a on d.task_type = a.task_type where t.group_id = ?";
    private final String sys134 = "select t.uuid,t.id,t.up_id,t.domain_id,r.group_id,r.task_id,d.task_desc,d.code_number from dispatch_task_dependency t inner join dispatch_group_task_relation r on t.up_id = r.id inner join dispatch_task_define d on r.task_id = d.task_id where t.id = ?";
    private final String sys135 = "select t.id,t.arg_id,t.arg_value,t.domain_id,r.group_id,r.task_id from dispatch_group_argument_rel t inner join dispatch_group_task_relation r on t.id = r.id where t.id = ?";
    private final String sys136 = "select t.task_id from dispatch_group_task_relation t where t.id = ?";
    private final String sys137 = "select t.id,t.batch_id,t.group_id,t.domain_id,d.group_desc,d.code_number from dispatch_batch_group_relation t inner join dispatch_group_define d on t.group_id = d.group_id where batch_id = ?";
    private final String sys138 = "select t.uuid,t.id,t.up_id,t.domain_id,r.group_id,d.group_desc,d.code_number from dispatch_group_dependency t inner join dispatch_batch_group_relation r on t.up_id = r.id inner join dispatch_group_define d on r.group_id = d.group_id where t.id = ?";
    private final String sys139 = "select distinct r.batch_id,d.arg_id,d.code_number,d.arg_desc,a.arg_value,a.uuid,d.bind_as_of_date,r.domain_id from dispatch_batch_group_relation r inner join dispatch_group_task_relation g on r.group_id=g.group_id inner join dispatch_task_argument_rel t on g.task_id = t.task_id inner join dispatch_argument_define d on t.arg_id = d.arg_id left join dispatch_batch_argument_rel a on a.batch_id = r.batch_id and a.arg_id = t.arg_id where d.arg_type = '4' and r.batch_id = ?";
    private final String sys140 = "update dispatch_batch_define set batch_status = ? where batch_id = ?";
    private final String sys141 = "update dispatch_task_argument_rel set sort_id = ? where uuid = ?";
    private final String sys142 = "delete from dispatch_task_argument_rel where uuid = ?";
    private final String sys143 = "select arg_id,arg_desc,arg_value,arg_type,domain_id from dispatch_argument_define where arg_id = ?";
    private final String sys144 = "insert into dispatch_task_argument_rel(uuid,task_id,arg_id,domain_id,arg_value,sort_id) values(replace(uuid(),'-',''),?,?,?,?,?)";
    private final String sys145 = "update dispatch_task_argument_rel set arg_value = ? where uuid = ?";
    private final String sys146 = "update dispatch_group_argument_rel set arg_value = ? where id = ? and arg_id = ?";
    private final String sys147 = "delete from dispatch_group_task_relation where id = ?";
    private final String sys148 = "insert into dispatch_group_task_relation(id,group_id,task_id,domain_id) values(?,?,?,?)";
    private final String sys149 = "insert into dispatch_group_argument_rel(uuid,id,arg_id,arg_value,domain_id) values(replace(uuid(),'-',''),?,?,?,?)";
    private final String sys150 = "select t.id,t.group_id,t.task_id,d.task_desc,t.domain_id from dispatch_group_task_relation t inner join dispatch_task_define d on t.task_id = d.task_id where t.group_id = ?";
    private final String sys151 = "insert into dispatch_task_dependency(uuid,id,up_id,domain_id) values(replace(uuid(),'-',''),?,?,?)";
    private final String sys152 = "delete from dispatch_task_dependency where uuid = ?";
    private final String sys153 = "delete from dispatch_group_dependency where uuid = ?";
    private final String sys154 = "insert into dispatch_batch_group_relation(id,batch_id,group_id,domain_id) values(?,?,?,?)";
    private final String sys155 = "delete from dispatch_batch_group_relation where id = ?";
    private final String sys156 = "insert into dispatch_group_dependency(uuid,id,up_id,domain_id) values(replace(uuid(),'-',''),?,?,?)";
    private final String sys157 = "select as_of_date from dispatch_batch_define where batch_id = ?";
    private final String sys158 = "insert into dispatch_batch_argument_rel(uuid,batch_id,arg_id,arg_value,domain_id) values(replace(uuid(),'-',''),?,?,?,?)";
    private final String sys159 = "select count(*) from dispatch_batch_argument_rel where batch_id = ? and arg_id = ?";
    private final String sys160 = "update dispatch_batch_argument_rel set arg_value = ? where batch_id = ? and arg_id = ?";
    private final String sys161 = "update dispatch_batch_define set as_of_date = ? where batch_id = ?";
    private final String sys162 = "select distinct replace(uuid(),'-','') as uuid,r.batch_id,d.arg_id,a.arg_value,r.domain_id,d.bind_as_of_date from dispatch_batch_group_relation r inner join dispatch_group_task_relation g on r.group_id=g.group_id inner join dispatch_task_argument_rel t on g.task_id = t.task_id inner join dispatch_argument_define d on t.arg_id = d.arg_id left join dispatch_batch_argument_rel a on a.batch_id = r.batch_id and a.arg_id = t.arg_id where d.arg_type = '4' and r.domain_id = ?";
    private final String sys163 = "select t.uuid,t.batch_id,t.arg_id,t.arg_value,t.domain_id from dispatch_batch_argument_rel t where t.domain_id = ? and t.batch_id = ?";
    private final String sys164 = "select distinct replace(uuid(),'-','') as uuid,r.batch_id,d.arg_id,a.arg_value,r.domain_id,d.bind_as_of_date from dispatch_batch_group_relation r inner join dispatch_group_task_relation g on r.group_id=g.group_id inner join dispatch_task_argument_rel t on g.task_id = t.task_id inner join dispatch_argument_define d on t.arg_id = d.arg_id left join dispatch_batch_argument_rel a on a.batch_id = r.batch_id and a.arg_id = t.arg_id where d.arg_type = '4' and r.domain_id = ? and r.batch_id = ?";
    private final String sys165 = "select batch_id,code_number,batch_desc,batch_status, as_of_date,domain_id,ret_msg,start_date, complete_date, end_date from dispatch_batch_define where domain_id = ? and batch_status = '1'";
    private final String sys166 = "delete from dispatch_batch_job_status where batch_id = ?";
    private final String sys167 = "insert into dispatch_batch_job_status(batch_id,job_id,status,gid,tid) values(?,?,?,?,?)";
    private final String sys168 = "update dispatch_batch_job_status set status = ? where batch_id = ? and job_id = ?";
    private final String sys169 = "select status from dispatch_batch_job_status t where batch_id = ? and job_id = ?";
    private final String sys170 = "select count(*) from dispatch_batch_job_status t where batch_id = ?";
    private final String sys171 = "select count(*) from dispatch_batch_job_status t where batch_id = ? and status = '2'";
    private final String sys172 = "delete from dispatch_batch_group_status where batch_id = ?";
    private final String sys173 = "insert into dispatch_batch_group_status(batch_id,gid,status) values(?,?,?)";
    private final String sys174 = "update dispatch_batch_group_status set status = ? where batch_id = ? and gid = ?";
    private final String sys175 = "select status from dispatch_batch_group_status where batch_id = ? and gid = ?";
    private final String sys176 = "update dispatch_batch_define set as_of_date = date_add(as_of_date,interval 1 day), batch_status = '1',start_date = now(), ret_msg = null, end_date = null where batch_id = ? and complete_date > as_of_date";
    private final String sys177 = "select count(*) from dispatch_batch_group_status where batch_id = ? and status = '2'";
    private final String sys178 = "update dispatch_batch_define set batch_status = '1',start_date = now(), ret_msg = null, end_date = null where batch_id = ? and complete_date >= as_of_date";
    private final String sys179 = "update dispatch_batch_define set ret_msg = ? , end_date = now(), batch_status = ? where batch_id = ?";
    private final String sys180 = "update dispatch_batch_define set batch_status = '4' where batch_status = '1'";
    private final String sys181 = "select config_id,config_desc,config_value,image,details from dispatch_batch_system_config";
    private final String sys182 = "select t.config_value from dispatch_batch_system_config t where t.config_id = ?";
    private final String sys183 = "select c.config_value from dispatch_batch_domain_config c where c.domain_id = ? and c.config_id = ?";
    private final String sys184 = "select count(*) from dispatch_batch_domain_config where uuid = ?";
    private final String sys185 = "update dispatch_batch_domain_config set config_value = ? where uuid = ?";
    private final String sys186 = "insert into dispatch_batch_domain_config(config_id,config_value,domain_id,uuid) values(?,?,?,?)";
    private final String sys187 = "select config_id,config_value,domain_id from dispatch_batch_domain_config where domain_id = ?";
    private final String sys188 = "update dispatch_batch_group_status set start_time = now(),status = ? where gid = ? and batch_id = ?";
    private final String sys189 = "update dispatch_batch_group_status set end_time = now(),status = ? where gid = ? and batch_id = ?";
    private final String sys190 = "update dispatch_batch_job_status set start_time = now(), status = ? where batch_id = ? and job_id = ?";
    private final String sys191 = "update dispatch_batch_job_status set end_time = now(), status = ? where batch_id = ? and job_id = ?";
    private final String sys192 = "insert into dispatch_batch_history(uuid,code_number,batch_id,batch_desc,batch_status,as_of_date,start_time,end_time,ret_msg,domain_id) select ?,code_number,batch_id,batch_desc,batch_status,as_of_date,start_date,end_date,ret_msg,domain_id from dispatch_batch_define where batch_id = ?";
    private final String sys193 = "select t.uuid,t.code_number,t.batch_id,t.batch_desc,t.batch_status,a.batch_status_desc,t.as_of_date,t.start_time,t.end_time,t.ret_msg,t.domain_id from dispatch_batch_history t inner join dispatch_batch_status_attr a on t.batch_status = a.batch_status where t.domain_id = ?";
    private final String sys194 = "delete from dispatch_batch_history where uuid = ?";
    private final String sys195 = "insert into dispatch_batch_group_history(id,gid,status,start_time,end_time) select ?,gid,status,start_time,end_time from dispatch_batch_group_status where batch_id = ?";
    private final String sys196 = "insert into dispatch_batch_job_history(id,job_id,status,start_time,end_time,gid,tid) select ?,job_id,status,start_time,end_time,gid,tid from dispatch_batch_job_status where batch_id = ?";
    private final String sys197 = "select t.id,t.gid,t.status,t.start_time,t.end_time,d.batch_status_desc as status_desc,g.group_id,e.group_desc from dispatch_batch_group_history t inner join dispatch_batch_group_relation g on t.gid = g.id inner join dispatch_group_define e on g.group_id = e.group_id left join dispatch_batch_status_attr d on t.status = d.batch_status where t.id = ?";
    private final String sys198 = "select count(*) from dispatch_batch_job_history where id = ? and gid = ?";
    private final String sys199 = "select count(*) from dispatch_batch_job_history where id = ? and gid = ? and status = '2'";
    private final String sys200 = "select t.id,t.job_id,t.status,t.start_time,t.end_time,t.gid,t.tid,a.batch_status_desc as status_desc,td.task_id,td.task_desc,td.task_type,c.task_type_desc from dispatch_batch_job_history t left join dispatch_batch_status_attr a on t.status = a.batch_status inner join dispatch_group_task_relation o on t.tid = o.id inner join dispatch_task_define td on o.task_id = td.task_id inner join dispatch_task_type_attr c on td.task_type = c.task_type where t.id = ? and t.gid = ?";
    private final String sys201 = "select t.batch_id,t.gid,t.status,t.start_time,t.end_time,d.batch_status_desc as status_desc,g.group_id,e.group_desc from dispatch_batch_group_status t inner join dispatch_batch_group_relation g on t.gid = g.id inner join dispatch_group_define e on g.group_id = e.group_id left join dispatch_batch_status_attr d on t.status = d.batch_status where t.batch_id = ?";
    private final String sys202 = "select count(*) from dispatch_batch_job_status where batch_id = ? and gid = ?";
    private final String sys203 = "select count(*) from dispatch_batch_job_status where batch_id = ? and gid = ? and status = '2'";
    private final String sys204 = "select t.batch_id,t.job_id,t.status,t.start_time,t.end_time,t.gid,t.tid,a.batch_status_desc as status_desc,td.task_id,td.task_desc,td.task_type,c.task_type_desc from dispatch_batch_job_status t left join dispatch_batch_status_attr a on t.status = a.batch_status inner join dispatch_group_task_relation o on t.tid = o.id inner join dispatch_task_define td on o.task_id = td.task_id inner join dispatch_task_type_attr c on td.task_type = c.task_type where t.batch_id = ? and t.gid = ?";
    private final String sys205 = "select t.batch_id,t.gid,t.status,t.start_time,t.end_time,d.batch_status_desc as status_desc,g.group_id,e.group_desc from dispatch_batch_group_status t inner join dispatch_batch_group_relation g on t.gid = g.id inner join dispatch_group_define e on g.group_id = e.group_id left join dispatch_batch_status_attr d on t.status = d.batch_status where t.batch_id = ? and t.gid = ?";
    private final String sys206 = "select t.batch_id,t.job_id,t.status,t.start_time,t.end_time,t.gid,t.tid,a.batch_status_desc as status_desc,td.task_id,td.task_desc,td.task_type,c.task_type_desc from dispatch_batch_job_status t left join dispatch_batch_status_attr a on t.status = a.batch_status inner join dispatch_group_task_relation o on t.tid = o.id inner join dispatch_task_define td on o.task_id = td.task_id inner join dispatch_task_type_attr c on td.task_type = c.task_type where t.batch_id = ? and t.gid = ? and t.tid = ?";
    private final String sys207 = "insert into sys_handle_logs(uuid,user_id,handle_time,client_ip,status_code,method,url,data,domain_id) values(replace(uuid(),'-',''),?,now(),?,?,?,?,?,?)";
    private final String sys208 = "select t.code_number,t.role_name,t.role_owner as create_user,t.role_create_date as create_date,a.role_status_desc,a.role_status_id as role_status_code, t.role_maintance_date as modify_date,t.role_maintance_user as modify_user,t.role_id from sys_role_info t inner join sys_role_status_attr a on t.role_status_id = a.role_status_id where t.role_id = ?";
    private final String sys209 = "select t.uuid, t.role_id, t.res_id, i.res_name,res_up_id from sys_role_resource t left join sys_resource_info i on t.res_id = i.res_id where t.role_id = ?";

    @Override
    public String getSqlText(String id) throws NoSuchFieldException, IllegalAccessException {
        Field field = MySqlDefine.class.getDeclaredField(id);
        return field.get(this).toString();
    }
}
