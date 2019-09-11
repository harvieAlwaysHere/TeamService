package top.harvie.ProjectTeam.dao.mapper;

import org.apache.ibatis.annotations.*;
import top.harvie.ProjectTeam.dao.pojo.FormTeam;

import java.io.IOException;
import java.util.List;

public interface FormTeamMapper {

    //增
    @Insert("INSERT INTO formteam (userid,teamid,createtime) VALUES (#{userid},#{teamid},#{createtime})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void add(FormTeam formteam)throws IOException;
    //删
    @Delete("DELETE FROM formteam WHERE id=#{id}")
    void delete(Integer id)throws IOException;
    //改
    @Update("UPDATE formteam SET userid=#{userid}, teamid=#{teamid}, status=#{status} WHERE id=#{id}")
    void update(FormTeam formteam)throws IOException;
    //查
    @Select( "SELECT * FROM formteam ORDER BY formteam.id DESC")
    List<FormTeam> selectAll();

    @Select("SELECT * FROM formteam WHERE id=#{id}")
    FormTeam select(Integer id);

    @Select("SELECT * FROM formteam WHERE userid=#{userid} ORDER BY formteam.id DESC")
    List<FormTeam> selectUserid(String userid);

    @Select("SELECT * FROM formteam WHERE teamid=#{teamid} ORDER BY formteam.id DESC")
    List<FormTeam> selectTeamid(String teamid);
}
