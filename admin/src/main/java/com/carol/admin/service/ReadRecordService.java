package com.carol.admin.service;

import com.carol.admin.dao.BaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * 悦读记录表
 * Generate By CodeGenerator
 * @author chris
 */
@Service
public class ReadRecordService extends BaseService{

    @Resource(name = "readRecordDao")
    @Override
    public void setBaseDao(BaseDao baseDao) {
       super.baseDao = baseDao;
    }
}