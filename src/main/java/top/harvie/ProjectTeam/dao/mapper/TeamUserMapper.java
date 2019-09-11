package top.harvie.ProjectTeam.dao.mapper;

import org.apache.ibatis.annotations.Select;
import top.harvie.ProjectTeam.dao.pojo.TeamUser;

import java.util.List;

public interface TeamUserMapper {

    @Select( "SELECT team.id,team.userid,team.time,team.readed,team.title,team.img,team.content,team.line,team.status,team.createtime,user.img as userimg,user.gender,user.name,user.wechatid,user.profile,user.position,user.phone,user.email,user.wechatline,user.location,user.status as userstatus,user.openid FROM team LEFT OUTER JOIN user ON(team.userid = user.id) ORDER BY team.id DESC")
    List<TeamUser> selectAll();

    @Select( "SELECT team.id,team.userid,team.time,team.readed,team.title,team.img,team.content,team.line,team.status,team.createtime,user.img as userimg,user.gender,user.name,user.wechatid,user.profile,user.position,user.phone,user.email,user.wechatline,user.location,user.status as userstatus,user.openid FROM team LEFT OUTER JOIN user ON(team.userid = user.id) WHERE team.id=#{id}")
    TeamUser selectById(Integer id);
}
