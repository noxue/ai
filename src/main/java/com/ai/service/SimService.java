package com.ai.service;

import com.ai.domain.bo.Sim;
import com.ai.domain.bo.SimUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SimService {
    //sim
    PageInfo<Sim> findAllSim(int pageNum, int pageSize, String uid, String phone);
    //条件查询simuser列表
    PageInfo<Sim> findSimUserById(int pageNum, int pageSize, String uid);

    //根据SimId查询simuser列表
    PageInfo<SimUser> findSimUserBySimId(int pageNum, int pageSize, String simId);

    //根据gatewayId查询sim列表
    PageInfo<Sim> findSimByGatewayId(int pageNum, int pageSize, int gatewayId);


    boolean registerSim(Sim sim);

    boolean editSim(Sim sim);

    boolean delSim(long id);

    Sim getSimById(long id);

    boolean isExistInSim(String user_id , String sim_id);


    //sim_user
    boolean registerSimUser(SimUser simUser);

    boolean editSimUser(SimUser simUser);

    boolean delSimUser(long id);

    boolean isExistInSimUser(String user_id , String sim_id);

    List<SimUser> getListByUserId(String userId);


}
