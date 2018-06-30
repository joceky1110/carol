package com.carol.admin.service;

import com.carol.admin.dao.BaseDao;
import com.carol.admin.dao.UserBabyDao;
import com.carol.model.UserBaby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户宝宝表
 * @author wwb
 */
@Service
public class UserBabyService extends BaseService{
//
//    @Autowired
//    private UserBabyDao userBabyDao;

    @Resource(name = "userBabyDao")
    @Override
    public void setBaseDao(BaseDao baseDao) {
       super.baseDao = baseDao;
    }

    public List<UserBaby> listByParentId(String id){
        String hql="select  DISTINCT t   from UserBaby t where t.parentId='" + id + "'" ;
        List<UserBaby> list = baseDao.find(hql);
        return list;
    }

}