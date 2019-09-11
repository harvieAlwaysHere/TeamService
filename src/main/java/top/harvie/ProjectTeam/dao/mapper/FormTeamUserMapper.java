package top.harvie.ProjectTeam.dao.mapper;

import org.apache.ibatis.annotations.Select;
import top.harvie.ProjectTeam.dao.pojo.FormTeamUser;
import top.harvie.ProjectTeam.dao.pojo.TeamUser;
import top.harvie.ProjectTeam.dao.pojo.UserFormTeam;

import java.util.List;

public interface FormTeamUserMapper {

    @Select( "SELECT team.id as teamid,team.userid as createuserid,team.time as teamtime,team.readed as teamreaded,team.title as teamtitle,team.img as teamimg,team.content as teamcontent,team.line as teamline,team.status as teamstatus,team.createtime as teamcreatetime,formteam.userid as formteamuserid,formteam.createtime as formteamcreatetime,formteam.status as formteamstatus,user.img as formteamuserimg,user.gender,user.name,user.wechatid,user.profile,user.position,user.phone,user.email,user.wechatline,user.location,user.status as formteamuserstatus,user.openid FROM team LEFT OUTER JOIN formteam ON(team.id = formteam.teamid) LEFT OUTER JOIN user ON(formteam.userid = user.id) WHERE team.userid=#{id} AND (formteam.teamid is not null) ORDER BY formteam.id DESC")
    List<FormTeamUser> selectById(Integer id);

    @Select( "SELECT formteam.teamid,formteam.createtime as formteamcreatetime,formteam.status as formteamstatus,team.userid as createteamuserid,team.time as teamtime,team.readed as teamreaded,team.title as teamtitle,team.img as teamimg,team.content as teamcontent,team.line as teamline,team.status as teamstatus,team.createtime as teamcreatetime,user.img as userimg,user.gender,user.name,user.wechatid,user.profile,user.position,user.phone,user.email,user.wechatline,user.location,user.status as userstatus,user.openid FROM formteam LEFT OUTER JOIN team ON(formteam.teamid = team.id) LEFT OUTER JOIN user ON(team.userid = user.id) WHERE formteam.userid=#{id} ORDER BY formteam.id DESC")
    List<UserFormTeam> selectTeamUserByUserId(String id);


}
