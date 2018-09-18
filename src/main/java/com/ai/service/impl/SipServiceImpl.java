package com.ai.service.impl;

import com.ai.dao.SipDao;
import com.ai.domain.bo.Sip;
import com.ai.service.SipService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SipService")
public class SipServiceImpl implements SipService {

    @Autowired
    private SipDao sipDao;

    @Override
    public PageInfo<Sip> findAllSip(int pageNum, int pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<Sip> SipsList = sipDao.getSipsList(name);
        PageInfo result = new PageInfo(SipsList);
        return result;
    }

    @Override
    public boolean registerSip(Sip Sip) {
        // 新增Sip
        return sipDao.insert(Sip) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean isSipExistByName(String name) {
        Sip Sip = sipDao.selectByName(name);
        return Sip != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean isSipExistByName(String name,String id) {
        Sip Sip = sipDao.selectByName(name);
        if(Sip!=null){
            if(Sip.getId().toString().equals(id)){
                return false;

            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean editSip(Sip Sip) {
        return sipDao.updateByPrimaryKeySelective(Sip) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delSip(long id) {
        return sipDao.deleteByPrimaryKey(id) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Sip getSipById(long id) {
        return sipDao.selectByPrimaryKey(id);
    }
}
