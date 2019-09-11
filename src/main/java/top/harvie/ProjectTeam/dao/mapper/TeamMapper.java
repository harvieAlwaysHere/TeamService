package top.harvie.ProjectTeam.dao.mapper;

import org.apache.ibatis.annotations.*;
import top.harvie.ProjectTeam.dao.pojo.Team;

import java.io.IOException;
import java.util.List;

public interface TeamMapper {

    //增
    @Insert("INSERT INTO team (userid, time, readed, title, img, content, line, status, createtime) VALUES (#{userid},#{time},#{readed},#{title},#{img},#{content},#{line},#{status},#{createtime})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void add(Team team)throws IOException;
    //删
    @Delete("DELETE FROM team WHERE id=#{id}")
    void delete(Integer id)throws IOException;
    //改
    @Update("UPDATE team SET userid=#{userid}, time=#{time}, readed=#{readed}, title=#{title}, img=#{img}, content=#{content}, line=#{line}, status=#{status}, createtime=#{createtime} WHERE id=#{id}")
    void update(Team team)throws IOException;
    //查
    @Select( "SELECT * FROM team WHERE status=1 ORDER BY team.id DESC")
    List<Team> selectAll();

    @Select("SELECT * FROM team WHERE id=#{id}")
    Team select(Integer id);

    @Select("SELECT * FROM team WHERE userid=#{id} and status=1 ORDER BY team.id DESC")
    List<Team> selectUserId(String id);



}
