package com.carol.admin.dao;

import com.carol.admin.base.QueryFilter;
import com.carol.model.ReadComment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础数据库操作
 * @param <T> 操作对象
 */
@Transactional
public abstract class BaseDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;// 泛型类的class
    private final String hql;// 查询语句
    private final String countHql;// 统计语句
    private static final String JOINFETCH = " LEFT JOIN FETCH ";// 预先抓取关键字
    private static final String JOIN = " LEFT JOIN ";// 级联查询关键字

    public String getCountHql() {
        return countHql;
    }

    public String getHql() {
        return hql;
    }

    /**
     * 构造时，告诉baseDao要查询表的类型，并且初始化查询和统计语句
     */
    public BaseDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.hql = " SELECT DISTINCT t FROM " + this.entityClass.getSimpleName() + " t ";// select DISTINCT t from Txxx t
        this.countHql = " SELECT COUNT(DISTINCT t) FROM " + this.entityClass.getSimpleName() + " t ";// select count(DISTINCT t) from Txxx t
    }

    /**
     * 更改hql和添加参数
     *
     * 调用方法后，hql的条件会变成 and t.xxx like :t_xxx这种形式，并且添加了params
     *
     * 如果有预先抓取参数，则hql还会加上例如 join fetch t.company as company类似的语句
     *
     * @param filter
     *            过滤器
     * @param params
     *            参数
     * @param hql
     *            查询语句
     * @return
     */
    public String changeHqlAndParams(QueryFilter filter, Map<String, Object> params, String hql) {
        hql += " WHERE 1=1 ";
        if (filter.getParams().size() > 0) {
            int paramIndex = 0;// 参数占位符索引
            for (Object[] o : filter.getParams()) {// filter.getParams()=[["t.name", "like", "%%孙%%"],["id", "in", [1, 2, 3, 4]]]
                if (o[1].toString().equalsIgnoreCase("In") || o[1].toString().equalsIgnoreCase("Not In")) {// In条件拼装，最终会拼装成类似 and id in (:_1,:_2,:_3,:_4)
                    List<Object> values = (ArrayList<Object>) o[2];
                    String inParamString = "";
                    for (int i = 0; i < values.size(); i++) {
                        if (i > 0) {
                            inParamString += " , ";
                        }
                        ++paramIndex;// 增加参数占位符索引
                        String paramName = "_" + paramIndex;
                        inParamString += " :" + paramName + " ";
                        params.put(paramName, values.get(i));
                    }
                    if (o[1].toString().equalsIgnoreCase("In")) {
                        hql += " AND " + o[0] + " IN (" + inParamString + ") ";
                    }
                    if (o[1].toString().equalsIgnoreCase("Not In")) {
                        hql += " AND " + o[0] + " NOT IN (" + inParamString + ") ";
                    }
                } else if (o[1].toString().equalsIgnoreCase("Is Null")) {// is null条件拼装
                    hql += " AND " + o[0] + " IS NULL ";
                } else if (o[1].toString().equalsIgnoreCase("Is Not Null")) {// is not null条件拼装
                    hql += " AND " + o[0] + " IS NOT NULL ";
                } else {
                    ++paramIndex;// 增加参数占位符索引
                    String paramName = "_" + paramIndex;
                    hql += " AND " + o[0] + " " + o[1] + " :" + paramName + " ";
                    params.put(paramName, o[2]);
                }
            }
        }
        return hql;
    }

    public void save(T t) {
          this.entityManager.persist(t);
    }

    public void saveAll(List<T> l) {
        if (l != null && l.size() > 0) {
            for (int i = 0; i < l.size(); i++) {
                this.save(l.get(i));
            }
        }
    }

    public void delete(T t) {
        this.entityManager.remove(t);
    }

    public void update(T t) {
        this.entityManager.merge(t);
    }

    public T get(String id) {
        return   this.entityManager.find(entityClass, id);
    }

    public T get(QueryFilter filter) {
        return this.get(filter, this.getHql());
    }

    public T get(QueryFilter filter, String newHql) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = newHql;
        hql = changeHqlAndParams(filter, params, hql);
        return this.getByHql(hql, params);
    }

    public T getByHql(String hql) {
        return this.getByHql(hql, null);
    }

    public T getByHql(String hql, Map<String, Object> params) {
        Query q = this.entityManager.createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = q.setFirstResult((1 - 1) * 1).setMaxResults(1).getResultList();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public List<T> find(String hql) {
        return this.find(hql, null, null, null);
    }

    public List<T> find() {
        return this.find(this.getHql(), null, null, null);
    }

    public List<T> find(String hql, Map<String, Object> params) {
        return this.find(hql, params, null, null);
    }

    public List<T> find(Map<String, Object> params) {
        return this.find(this.getHql(), params, null, null);
    }

    public List<T> find(QueryFilter filter) {
        return this.find(filter, this.getHql());
    }

    public List<T> find(QueryFilter filter, String newHql) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = newHql;
        hql = changeHqlAndParams(filter, params, hql);
        if (StringUtils.isNotBlank(filter.getSort())) {// 如果有排序需求，这拼装排序条件
            hql += " ORDER BY " + filter.getSort();
        }
        return this.find(hql, params, filter.getPage(), filter.getPageSize());
    }

    public List<T> find(String hql, Integer page, Integer pageSize) {
        return this.find(hql, null, page, pageSize);
    }

    public List<T> find(String hql, Map<String, Object> params, Integer page, Integer pageSize) {
        Query q = this.entityManager.createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        if (page == null || pageSize == null) {
            return q.getResultList();
        }
        return q.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).getResultList();
    }

    public Long count(String hql) {
        return this.count(hql, null);
    }

    public Long count() {
        return this.count(this.getCountHql());
    }

    public Long count(String hql, Map<String, Object> params) {
        Query q = this.entityManager.createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return  (Long)q.getSingleResult();
    }

    public Long count(QueryFilter filter) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = this.getCountHql();
        hql = changeHqlAndParams(filter, params, hql);
        return this.count(hql.replaceAll(JOINFETCH, JOIN), params);// 统计是不可以预先抓取的，所以要更换hql
    }

    public Long count(QueryFilter filter, String newHql) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = newHql;
        hql = changeHqlAndParams(filter, params, hql);
        return this.count(hql.replaceAll(JOINFETCH, JOIN), params);// 统计是不可以预先抓取的，所以要更换hql
    }

    public int executeHql(String hql) {
        return this.executeHql(hql, null);
    }

    public int executeHql(String hql, Map<String, Object> params) {
        Query q = this.entityManager.createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }


    public int executeSql(String sql) {
        return this.executeSql(sql, null);
    }

    public int executeSql(String sql, Map<String, Object> params) {
        Query q = this.entityManager.createNativeQuery( sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    public int countBySql(String sql) {
        return this.countBySql(sql, null);
    }

    public int countBySql(String sql, Map<String, Object> params) {
         Query q = entityManager.createNativeQuery( sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.getFirstResult();
    }

    /**
     * 执行sql
     * @param sql
     * @param params
     * @param clazz
     * @return
     */
    public  List<T> nativeQuery(String sql, Map<String, Object> params,Class<T> clazz){
        //创建原生SQL查询QUERY实例,指定了返回的实体类型
        Query query = entityManager.createNativeQuery(sql,ReadComment.class);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        //执行查询，返回的是实体列表,
        List list = query.getResultList();
        for(int i=0;i<list.size();i++) {
            Object[] obj = (Object[]) list.get(i);
            //使用obj[0],obj[1],obj[2]取出属性   fixme
        }

        return list;
    }
}
