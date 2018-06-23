package com.ai.service;

import com.ai.domain.bo.Sim;
import com.ai.domain.bo.SimUser;
import com.github.pagehelper.PageInfo;

public interface SimService {
    //sim
    PageInfo<Sim> findAllSim(int pageNum, int pageSize, String uid);

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


}
