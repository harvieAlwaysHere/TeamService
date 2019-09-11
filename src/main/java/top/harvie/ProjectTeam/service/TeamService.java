package top.harvie.ProjectTeam.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.harvie.ProjectTeam.dao.mapper.TeamMapper;
import top.harvie.ProjectTeam.dao.mapper.TeamUserMapper;
import top.harvie.ProjectTeam.dao.pojo.Team;
import top.harvie.ProjectTeam.dao.pojo.TeamUser;

import java.util.List;

@Service
@CacheConfig(cacheNames = "team")
public class TeamService {

    private static Logger log = LogManager.getLogger(TeamService.class);

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    TeamUserMapper teamUserMapper;

    //增
    @Caching(evict={@CacheEvict(key="'TeamCache'"),
            @CacheEvict(key="'TeamUserCache'")})
    public Integer add(Team team) throws Exception
    {
        teamMapper.add(team);
        return team.getId();
    }
    //删
    @Caching(evict={@CacheEvict(key="'TeamCache'"),
            @CacheEvict(key="'TeamUserCache'")})
    public void delete(Integer id) throws Exception
    {
        teamMapper.delete(id);
    }
    //改
    @Caching(evict={@CacheEvict(key="'TeamCache'"),
            @CacheEvict(key="'TeamUserCache'")})
    public void update(Team team) throws Exception
    {
        teamMapper.update(team);
    }
    //查
    public Team select(Integer id)
    {
        Team team=teamMapper.select(id);
        return team;
    }

    public List<Team> selectUserId(String id)
    {
        List<Team> teamList=teamMapper.selectUserId(id);
        return teamList;
    }

    @Cacheable(key="'TeamCache'")
    public List<Team> select()
    {
        List<Team> teamList=teamMapper.selectAll();
        return teamList;
    }

    @Cacheable(key="'TeamUserCache'")
    public List<TeamUser> selectTeamUser()
    {
        List<TeamUser> teamUserList=teamUserMapper.selectAll();
        return teamUserList;
    }

    public TeamUser selectTeamUser(Integer id)
    {
        TeamUser teamUser=teamUserMapper.selectById(id);
        return teamUser;
    }
}
