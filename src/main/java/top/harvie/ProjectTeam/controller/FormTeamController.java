package top.harvie.ProjectTeam.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import top.harvie.ProjectTeam.conf.Tools;
import top.harvie.ProjectTeam.dao.pojo.FormTeam;
import top.harvie.ProjectTeam.dao.pojo.FormTeamUser;
import top.harvie.ProjectTeam.dao.pojo.UserFormTeam;
import top.harvie.ProjectTeam.service.FormTeamService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
@Api(value="FormTeam",description = "加入组队接口")
public class FormTeamController {

    @Autowired
    FormTeamService formteamService;

    //增加一条组队条目
    @PostMapping(value = "/formteam")
    @ApiOperation(value="用于增加一个组队条目",notes="用于增加一个组队条目")
    public Map add(
            @ApiParam(required = true,name="formteam",value = "组队信息(不需要填入id,status,createtime)") @RequestBody FormTeam formteam
    ){
        Map<String,Object> response=new HashMap<>();
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formteam.setCreatetime(df.format(new Date()));
            formteam.setStatus("0");
            formteamService.add(formteam);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",formteam);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //根据ID删除一条组队信息
    @PostMapping(value = "/formteam/delete")
    @ApiOperation(value="根据ID删除指定组队条目",notes="根据ID删除指定组队条目")
    public Map delete(
            @ApiParam(required = true,name="id",value = "需要删除的组队的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            formteamService.delete(id);
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
    @PostMapping(value = "/formteam/update")
    @ApiOperation(value="根据ID更新指定组队条目",notes="根据ID更新指定组队条目")
    public Map update(
            @ApiParam(required = true,name="formteam",value = "需要更新的组队信息(ID必填)") @RequestBody FormTeam formteam
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            Integer id=formteam.getId();
            FormTeam updateTeam=formteamService.select(id);
            Tools.update(updateTeam,formteam);
            formteamService.update(updateTeam);
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
    @GetMapping(value = "/formteam/all")
    @ApiOperation(value="询所有组队信息",notes="询所有组队信息")
    public Map select(){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<FormTeam> teamList=formteamService.select();
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

    @GetMapping(value = "/formteam")
    @ApiOperation(value="查询指定ID组队信息",notes="查询指定ID组队信息")
    public Map selectById(
            @ApiParam(required = true,name="id",value = "需要查询的组队的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            FormTeam formteam=formteamService.select(id);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",formteam);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    @GetMapping(value = "/formteam/user")
    @ApiOperation(value="查询指定userid组队信息",notes="查询userid组队信息")
    public Map selectByUserId(
            @ApiParam(required = true,name="userid",value = "需要查询的组队的userid") @RequestParam("userid") String userid
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<FormTeam> teamList=formteamService.selectUserid(userid);
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

    @GetMapping(value = "/formteam/team")
    @ApiOperation(value="查询指定teamid组队信息",notes="查询teamid组队信息")
    public Map selectByTeamId(
            @ApiParam(required = true,name="teamid",value = "需要查询的组队的teamid") @RequestParam("teamid") String teamid
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<FormTeam> teamList=formteamService.selectTeamid(teamid);
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

    @GetMapping(value = "/formteamuser")
    @ApiOperation(value="查询响应指定userid发起的组队的用户信息",notes="查询响应指定userid发起的组队的用户信息")
    public Map selectFormTeamByUserId(
            @ApiParam(required = true,name="userid",value = "查询响应指定userid发起的组队的用户信息") @RequestParam("userid") Integer userid
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<FormTeamUser> formTeamUserList=formteamService.selectFormTeamByUserid(userid);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",formTeamUserList);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    @GetMapping(value = "/userformteam")
    @ApiOperation(value="查询指定userid响应队伍的信息及队长信息",notes="查询指定userid响应队伍的信息及队长信息")
    public Map selectUserFormTeamByUserId(
            @ApiParam(required = true,name="userid",value = "查询指定userid响应队伍的信息及队长信息") @RequestParam("userid") String userid
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<UserFormTeam> userFormTeamUserList=formteamService.selectTeamUserByUserid(userid);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",userFormTeamUserList);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }
}
