package com.carol.admin.service;

import com.carol.admin.dao.BaseDao;
import com.carol.model.ReadComment;
import com.carol.vo.ReadCommentVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 悦读评论
 * Generate By CodeGenerator
 * @author chris
 */
@Service
public class ReadCommentService extends BaseService{

    @Resource(name = "readCommentDao")
    @Override
    public void setBaseDao(BaseDao baseDao) {
       super.baseDao = baseDao;
    }

    public List<ReadComment> listByRecordId(String recordId){
        String hql="select  DISTINCT t   from ReadComment t where t.recordId='" + recordId + "'" ;
//        String sql="select  *   from read_comment t where t.record_id= '" + recordId + "'"  ;
        List<ReadComment> list = baseDao.find(hql);

//        Map map = new HashMa

//        List<ReadCommentVo> list = baseDao.nativeQuery(sql,null,ReadCommentVo.class);
        return list;
    }
}