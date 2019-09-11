package top.harvie.ProjectTeam.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.harvie.ProjectTeam.dao.mapper.FormTeamMapper;
import top.harvie.ProjectTeam.dao.mapper.FormTeamUserMapper;
import top.harvie.ProjectTeam.dao.pojo.FormTeam;
import top.harvie.ProjectTeam.dao.pojo.FormTeamUser;
import top.harvie.ProjectTeam.dao.pojo.UserFormTeam;

import java.util.List;

@Service
@CacheConfig(cacheNames = "formteam")
public class FormTeamService {

    private static Logger log = LogManager.getLogger(FormTeamService.class);

    @Autowired
    FormTeamMapper formteamMapper;

    @Autowired
    FormTeamUserMapper formTeamUserMapper;

    //增
    @CacheEvict(key="'FormTeamCache'")
    public Integer add(FormTeam formteam) throws Exception
    {
        formteamMapper.add(formteam);
        return formteam.getId();
    }
    //删
    @CacheEvict(key="'FormTeamCache'")
    public void delete(Integer id) throws Exception
    {
        formteamMapper.delete(id);
    }
    //改
    @CacheEvict(key="'FormTeamCache'")
    public void update(FormTeam formteam) throws Exception
    {
        formteamMapper.update(formteam);
    }
    //查
    public FormTeam select(Integer id)
    {
        FormTeam formteam=formteamMapper.select(id);
        return formteam;
    }

    public List<FormTeam> selectUserid(String userid)
    {
        List<FormTeam> teamList=formteamMapper.selectUserid(userid);
        return teamList;
    }

    public List<FormTeam> selectTeamid(String teamid)
    {
        List<FormTeam> teamList=formteamMapper.selectTeamid(teamid);
        return teamList;
    }

    @Cacheable(key="'TeamCache'")
    public List<FormTeam> select()
    {
        List<FormTeam> teamList=formteamMapper.selectAll();
        return teamList;
    }

    //根据userid查询所创建队伍的其他人的响应信息
    public List<FormTeamUser> selectFormTeamByUserid(Integer userid)
    {
        List<FormTeamUser> formTeamList=formTeamUserMapper.selectById(userid);
        return formTeamList;
    }

    //根据userid查询所响应队伍的队伍及队伍创建人的信息
    public List<UserFormTeam> selectTeamUserByUserid(String userid)
    {
        List<UserFormTeam> userFormTeamList=formTeamUserMapper.selectTeamUserByUserId(userid);
        return userFormTeamList;
    }
}
