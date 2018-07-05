package com.ai.service.impl;

import com.ai.dao.SimDao;
import com.ai.dao.SimUserDao;
import com.ai.dao.SimUserMapper;
import com.ai.domain.bo.Sim;
import com.ai.domain.bo.SimUser;
import com.ai.service.SimService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SimService")
public class SimServiceImpl implements SimService {

    @Autowired
    private SimDao simDao;

    @Autowired
    private SimUserMapper simUserMapper;

    @Autowired
    private SimUserDao simUserDao;

    @Override
    public PageInfo<Sim> findAllSim(int pageNum, int pageSize, String uid ,String phone) {
        PageHelper.startPage(pageNum, pageSize);
        List<Sim> simsList = simDao.getSimsList(uid ,phone);
        PageInfo result = new PageInfo(simsList);
        return result;
    }

    @Override
    public PageInfo<Sim> findSimUserById(int pageNum, int pageSize, String uid) {
        PageHelper.startPage(pageNum, pageSize);
        List<Sim> simUserList = simDao.getSimUserList(uid );
        PageInfo result = new PageInfo(simUserList);
        return result;
    }

    @Override
    public boolean registerSim(Sim sim) {
        return simDao.insert(sim) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editSim(Sim sim) {
        return simDao.updateByPrimaryKeySelective(sim) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delSim(long id) {
        return simDao.deleteByPrimaryKey(id) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Sim getSimById(long id) {
        return simDao.selectByPrimaryKey(id);
    }

    //SimUser
    @Override
    public boolean registerSimUser(SimUser simUser) {
        return simUserMapper.insert(simUser) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editSimUser(SimUser simUser) {

        return simUserMapper.updateByPrimaryKeySelective(simUser) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delSimUser(long id) {
        return simUserMapper.deleteByPrimaryKey(id) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean isExistInSim(String user_id, String sim_id) {
        return simDao.getSimBySimIdAndUserId(user_id,Integer.parseInt(sim_id))==null ? Boolean.FALSE: Boolean.TRUE;
    }

    @Override
    public boolean isExistInSimUser(String user_id, String sim_id) {
        return simUserDao.getSimUserBySimIdAndUserId(user_id,Long.parseLong(sim_id))==null ? Boolean.FALSE: Boolean.TRUE;
    }
}
