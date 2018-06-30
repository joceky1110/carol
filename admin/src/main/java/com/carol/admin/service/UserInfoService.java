package com.carol.admin.service;

import com.carol.admin.dao.BaseDao;
import com.carol.model.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * 用户表
 * Generate By CodeGenerator
 * @author wwb
 */
@Service
public class UserInfoService<UserInfo> extends BaseService{

    @Resource(name = "userInfoDao")
    @Override
    public void setBaseDao(BaseDao baseDao) {
       super.baseDao = baseDao;
    }

}