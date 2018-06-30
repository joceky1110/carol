package com.carol.admin.service;

import com.carol.admin.base.JsonResult;
import com.carol.admin.base.QueryFilter;
import com.carol.admin.dao.BaseDao;

import java.util.List;


/**
 * 服务层基类
 */
public abstract class BaseService<T> {

    protected BaseDao<T> baseDao;

    public abstract void  setBaseDao(BaseDao<T> setBaseDao);

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    /**
     * 保存
     * @param t
     * @return
     */
    public JsonResult save(T t){
        JsonResult jsonResult = new JsonResult();
        try{
            baseDao.save(t);
            jsonResult.setObj(t);
            jsonResult.setMsg("保存成功");
            jsonResult.setSuccess(true);
        }catch (Exception e){
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.getMessage());
        }
        return  jsonResult;
    }

    /**
     * 更新一个对象
     * @param t
     * @return
     */
    public JsonResult saveOrUpdate(T t){
        JsonResult jsonResult = new JsonResult();
        try{
            baseDao.update(t);
            jsonResult.setObj(t);
        }catch (Exception e){
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.getMessage());
        }
        return  jsonResult;
    }

    /**
     * 按照ID查询
     * @param id
     * @return
     */
    public T get(String id){
      return  this.baseDao.get(id);
    }

    public T get(QueryFilter filter){
        return this.baseDao.get(filter);
    }

    /**
     * 查询所有
     * @param queryFilter
     * @return
     */
    public List<T> getAll(QueryFilter queryFilter){
        return  this.baseDao.find(queryFilter);
    }


    public   List find(QueryFilter filter){
        return this.baseDao.find(filter);
    }

    public   Long count(QueryFilter filter){
        return this.baseDao.count(filter);
    }

    public void update(T t) {
          this.baseDao.update(t);
    }

    public void delete(T t) {
        this.baseDao.delete(t);
    }
}
