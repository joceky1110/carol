package com.carol.admin.service;

import com.carol.admin.dao.BaseDao;
import com.carol.model.ReadLabel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 悦读标签表
 * Generate By CodeGenerator
 * @author chris
 */
@Service
public class ReadLabelService extends BaseService{

    @Resource(name = "readLabelDao")
    @Override
    public void setBaseDao(BaseDao baseDao) {
       super.baseDao = baseDao;
    }

    public List<ReadLabel> getAll() {
        return baseDao.find();
    }
}