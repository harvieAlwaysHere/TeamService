package top.harvie.ProjectTeam.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import top.harvie.ProjectTeam.conf.Tools;
import top.harvie.ProjectTeam.dao.pojo.Team;
import top.harvie.ProjectTeam.dao.pojo.TeamUser;
import top.harvie.ProjectTeam.service.TeamService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
@Api(value="Team",description = "发起组队接口")
public class TeamController {


    @Autowired
    TeamService teamService;

    //增加一条组队条目
    @PostMapping(value = "/team")
    @ApiOperation(value="用于增加组队条目",notes="用于增加组队条目")
    public Map add(
            @ApiParam(required = true,name="team",value = "组队信息(不需要填入ID,status,createtime，readed)") @RequestBody Team team
    ){
        Map<String,Object> response=new HashMap<>();
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            team.setCreatetime(df.format(new Date()));
            team.setStatus("1");
            team.setReaded("0");
            teamService.add(team);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",team);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //根据ID删除一条组队信息
    @PostMapping(value = "/team/delete")
    @ApiOperation(value="根据ID删除指定组队条目",notes="根据ID删除指定组队条目")
    public Map delete(
            @ApiParam(required = true,name="id",value = "需要删除的组队的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            teamService.delete(id);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",null);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //更新组队信息
    @PostMapping(value = "/team/update")
    @ApiOperation(value="根据ID更新指定组队条目",notes="根据ID更新指定组队条目")
    public Map update(
            @ApiParam(required = true,name="team",value = "需要更新的组队信息(ID必填)") @RequestBody Team team
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            Integer id=team.getId();
            Team updateTeam=teamService.select(id);
            Tools.update(updateTeam,team);
            teamService.update(updateTeam);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",updateTeam);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //查询所有组队信息
    @GetMapping(value = "/team/all")
    @ApiOperation(value="询所有组队信息",notes="询所有组队信息")
    public Map select(){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<Team> teamList=teamService.select();
            response.put("status","success");
            response.put("massage",null);
            response.put("data",teamList);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    @GetMapping(value = "/team")
    @ApiOperation(value="查询指定ID组队信息",notes="查询指定ID组队信息")
    public Map selectById(
            @ApiParam(required = true,name="id",value = "需要查询的组队的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            Team team=teamService.select(id);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",team);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    @GetMapping(value = "/team/user")
    @ApiOperation(value="查询指定userid组队信息",notes="查询指定userid组队信息")
    public Map selectByUserId(
            @ApiParam(required = true,name="userid",value = "需要查询的userid") @RequestParam("userid") String userid
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<Team> teamList=teamService.selectUserId(userid);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",teamList);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //查询所有组队信息
    @GetMapping(value = "/teamuser/all")
    @ApiOperation(value="询所有组队和用户信息",notes="询所有组队和用户信息")
    public Map selectTeamUser(){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<TeamUser> teamUserList=teamService.selectTeamUser();
            response.put("status","success");
            response.put("massage",null);
            response.put("data",teamUserList);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    @GetMapping(value = "/teamuser")
    @ApiOperation(value="查询指定ID组队和用户信息",notes="查询指定ID组队和用户信息")
    public Map selectTeamUserById(
            @ApiParam(required = true,name="id",value = "需要查询的组队的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            TeamUser teamUser=teamService.selectTeamUser(id);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",teamUser);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }
}
