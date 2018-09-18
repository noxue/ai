package com.ai.service.impl;

import com.ai.dao.SipDao;
import com.ai.dao.SipUserDao;
import com.ai.domain.bo.Sip;
import com.ai.domain.bo.SipUser;
import com.ai.service.SipUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SipUserService")
public class SipUserServiceImpl implements SipUserService {

    @Autowired
    private SipUserDao sipUserDao;

    @Autowired
    private SipDao sipDao;

    @Override
    public PageInfo<SipUser> findAllSipUser(int pageNum, int pageSize, String user_id) {
        PageHelper.startPage(pageNum, pageSize);
        List<SipUser> SipUsersList = sipUserDao.selectByUid(user_id);
        PageInfo result = new PageInfo(SipUsersList);
        return result;
    }

    @Override
    public List<Sip> findAllSipUser(String user_id) {
        List<SipUser> SipUsersList = sipUserDao.selectByUid(user_id);
        List<Sip> sipList = new ArrayList<>();
        if(SipUsersList != null){
            for (int i=0; i<SipUsersList.size();i++){
                Sip sip = new Sip();
                sip = sipDao.selectByPrimaryKey(SipUsersList.get(i).getSipId());
                if(sip != null){
                    sipList.add(sip);
                }
            }
        }
        return sipList;
    }

    @Override
    public List<SipUser> findSipUser(String user_id) {
        return sipUserDao.selectByUid(user_id);
    }

    @Override
    public boolean checkRepeat(String user_id, String sip_id) {
        if(sipUserDao.isRepeat(user_id,sip_id)!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean registerSipUser(SipUser SipUser) {
        // 新增SipUser
        return sipUserDao.insert(SipUser) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editSipUser(SipUser SipUser) {
        return sipUserDao.updateByPrimaryKeySelective(SipUser) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delSipUser(long id) {
        return sipUserDao.deleteByPrimaryKey(id) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public SipUser getSipUserById(long id) {
        return sipUserDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean insertSipUserList(List<SipUser> list) {
        return sipUserDao.insertBatch(list) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delSipUserList(List<String> list) {
        return sipUserDao.deleteBySipId(list) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
