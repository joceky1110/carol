package com.carol.admin.controller;


import com.carol.admin.base.QueryFilter;
import com.carol.admin.service.ReadLabelService;
import com.carol.model.ReadLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommonController {

    @Autowired
    private ReadLabelService readLabelService;
    /**
     *
     * 字典表
     * @return
     */
    @RequestMapping("/static/js/dict")
    public void dict(HttpServletResponse resp) {
        try{
            String script = "var map = {};" +
                    "map['label']={};";
            List<ReadLabel> list = readLabelService.getAll();
            for(ReadLabel d:list){
                script +=" map['label']['"+d.getLabelCode()+"']='"+d.getLabelName()+"';\n\r";
            }
//            QueryFilter qf =  new QueryFilter();
//            qf.addFilter("Q_parentId_=_Long","2");
//            Map<SystemDict,List<SystemDict>> dictMap = buildDictMap(systemDictService.getAll(qf));
//            for(SystemDict dict:dictMap.keySet()){
//                script +=" map['"+dict.getDictCode()+"']={};\n\r";
//                for(SystemDict subDict:dictMap.get(dict)){
//                    script +=" map['"+dict.getDictCode()+"']['"+subDict.getDictCode()+"']='"+subDict.getDictName()+"';\n\r";
//                }
//            }
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().append(script);
            resp.flushBuffer();
        }catch (Exception e){
        }
    }

//    private Map<SystemDict,List<SystemDict>> buildDictMap(List<SystemDict> list) {
//        Map<SystemDict,List<SystemDict>> map = new HashMap<SystemDict,List<SystemDict>>();
//        /**
//         * 填充子項
//         */
//        for(SystemDict d:list){
//            if(d.getParentId()!=null&&d.getParentId()==2L){
//               QueryFilter qf =  new QueryFilter();
//                qf.addFilter("Q_parentId_=_Long",d.getId().toString());
//                map.put(d , systemDictService.getAll(qf));
//            }
//        }
//        return map;
//    }
}
