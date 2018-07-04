package com.carol.admin.controller;

import com.carol.admin.base.DateTimePropertyEditorSupport;
import com.carol.admin.base.JsonResult;
import com.carol.admin.base.PageTable;
import com.carol.admin.base.QueryFilter;
import com.carol.admin.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 控制层基类
 */
public abstract class BaseCtrl<T>{

    protected BaseService<T> service;

    public abstract void setService(BaseService<T> service);

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());

        /**
         * 防止XSS攻击，并且带去左右空格功能
         */
        //binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
    }

    public PageTable find(QueryFilter filter) {
        PageTable p = new PageTable();
        p.setData(service.find(filter));
        p.setCount(service.count(filter));
        p.setCode(0);
        p.setMsg("success");
        return p;
    }

    public   JsonResult add(T t){
        JsonResult j = new JsonResult();
        try {
            j = service.save(t);
        } catch (Exception e) {
            j.setMsg("保存失败！");
            e.printStackTrace();
        }
        return j;
    }

    public   JsonResult update(T t){
        JsonResult j = new JsonResult();
        T ot = null;
        try{
            Method m = t.getClass().getMethod("getId" );
            String id  = (String) m.invoke(t);
            ot = service.get(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (ot != null) {
            try {
                //TODO
//                BeanUtils.copyProperties(t, ot, new String[] { "createTime"});// 一句话将新对象信息覆盖到原数据中,但是创建时间是不需要更新的
                BeanUtils.copyProperties(t, ot,getNullPropertyNames(t));//将新对象信息覆盖到原数据中 null值忽略 不覆盖
                service.update(ot);
                j.setMsg("更新成功");
                j.setSuccess();
                j.setData(t);
            } catch (Exception e) {
                j.setMsg("更新失败！");
                e.printStackTrace();
            }
        } else {
            j.setMsg("更新失败，找不到对象");
        }
        return j;
    }

    public JsonResult delete(QueryFilter filter) {
        JsonResult j = new JsonResult();
        try {
            service.delete(service.get(filter));
            j.setMsg("删除成功");
            j.setSuccess();
        } catch (Exception e) {
            j.setMsg("删除失败");
        }
        return j;
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
