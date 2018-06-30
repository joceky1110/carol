package com.carol.admin.service;

import com.carol.admin.dao.BaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * 专栏文章表
 * Generate By CodeGenerator
 * @author chris
 */
@Service
public class ArticleService extends BaseService{

    @Resource(name = "articleDao")
    @Override
    public void setBaseDao(BaseDao baseDao) {
       super.baseDao = baseDao;
    }
}