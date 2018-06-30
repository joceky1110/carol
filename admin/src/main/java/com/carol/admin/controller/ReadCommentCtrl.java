package com.carol.admin.controller;

import com.carol.admin.base.JsonResult;
import com.carol.admin.base.PageTable;
import com.carol.admin.base.QueryFilter;
import com.carol.admin.service.BaseService;
import com.carol.admin.service.ReadCommentService;
import com.carol.admin.service.UserInfoService;
import com.carol.model.ReadComment;
import com.carol.model.UserInfo;
import com.carol.vo.ReadCommentVo;
import com.carol.vo.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 悦读评论控制层
 * @author chris
 */
@Controller
public class ReadCommentCtrl extends BaseCtrl<ReadComment>{

    @Autowired
    private UserInfoService userInfoService;

    @Resource(name="readCommentService")
    @Override
    public void setService(BaseService service) {
          super.service = service;
    }

    @Autowired
    private ReadCommentService readCommentService;


    /**
     * 列出文章评论
     * @param request
     * @return
     */
    @RequestMapping("/data/listRecordComm")
    @ResponseBody
    public PageTable listRecordComm(HttpServletRequest request,String recordId ) {
//        PageTable p = new PageTable();
//        List<ReadComment> commentList = readCommentService.listByRecordId(recordId);
////        p.setData(service.find(filter));
////        p.setCount(service.count(filter));
//        List<ReadCommentVo> treeList = generateTree(commentList);
//        p.setData(treeList);
//        p.setCode(0);
//        p.setMsg("success");
//        return p;

        QueryFilter filter = new QueryFilter(request);
        filter.addFilter("Q_t.recordId_=_String", recordId);
        filter.addFilter("Q_t.replyId_isNull", "" );//查询没有回复评论的评论
        PageTable p =  super.find(filter);

        List<ReadComment> list =  p.getData();
        List<ReadCommentVo> voList = new ArrayList<>();
        for(ReadComment comm:list){
            ReadCommentVo vo = new ReadCommentVo();
            BeanUtils.copyProperties(comm,vo);
            List<ReadComment> commentList = new ArrayList<>();
            findChildComm(vo.getRecordId(),vo.getId(),commentList);
            vo.setCommList(commentList);

            //追加用户头像
            Object obj = userInfoService.get(vo.getUserId());
            vo.setUserImg(((UserInfo)obj).getImg());
            voList.add(vo);
        }
        p.setData(voList);
        return p;

    }

   private void  findChildComm(String recordId,String pid,List<ReadComment> list){
        QueryFilter fl = new QueryFilter();
        fl.addFilter("Q_t.recordId_=_String", recordId);
        fl.addFilter("Q_t.replyId_=_String", pid );
        List<ReadComment> subList = service.find(fl);
        if(subList!=null && subList.size() > 0){
            list.addAll(subList);
            for(ReadComment comment:subList ){
                //递归遍历
                findChildComm(recordId,comment.getId(),list);
            }
        }
    }
    /**
     * 添加悦读评论页面
     * @return
     */
    @RequestMapping("/page/readComment/add")
    public String addPage() {
        return "page/readComment/add";
    }

    /**
     * 添加悦读评论动作
     * @return
     */
    @RequestMapping("/data/readComment/add")
    @ResponseBody
    public JsonResult add(ReadComment readComment) {
        return super.add(readComment);
    }

    /**
     * 编辑悦读评论页面
     * @return
     */
    @RequestMapping("/page/readComment/edit")
    public String edit(Model model, String id) {
        ReadComment  readComment = (ReadComment )service.get(id);
        model.addAttribute("readComment",readComment);
        return "page/readComment/edit";
    }

    /**
     * 编辑悦读评论动作
     * @return
     */
    @RequestMapping("/data/readComment/edit")
    @ResponseBody
    public JsonResult update( ReadComment readComment, HttpServletRequest request) {
        return super.update(readComment);
    }

    /**
     * 删除悦读评论动作
     * @return
     */
    @RequestMapping("/data/readComment/delete")
    @ResponseBody
    public JsonResult delete( HttpServletRequest request,Integer id) {
        QueryFilter filter = new QueryFilter(request);
        if (id != null) {
            filter.addFilter("Q_t.id_=_String", "" + id);
        }
        return super.delete(filter);
    }


//    /**
//     * 根据资源list， 生成树结构
//     * @param list
//     * @return
//     */
//    public List<ReadCommentVo> generateTree(List<ReadComment> list){
//        // 最后的结果
//        List<ReadCommentVo> rootList = new ArrayList<ReadCommentVo>();
//        List<ReadCommentVo> srcList = new ArrayList<ReadCommentVo>();
//        // 先找到所有的一级菜单
//        for (int i = 0; i < list.size(); i++) {
//            ReadCommentVo vo = new ReadCommentVo();
//            BeanUtils.copyProperties(list.get(i),vo);
//            // 一级菜单没有parentId
//            if (StringUtils.isBlank(list.get(i).getReplyId()) || list.get(i).getReplyId().equals("0")) {
//                rootList.add(vo);
//            }
//            srcList.add(vo);
//        }
//        // 为一级菜单设置子菜单，getChild是递归调用的
//        for (ReadCommentVo commentVo : rootList) {
//            commentVo.setCommList(getChild(commentVo.getId(), srcList));
//        }
//        return rootList;
//    }
//
//    /**
//     * 递归查找子菜单
//     * @param id
//     * @param list
//     * @return
//     */
//    private List<ReadCommentVo> getChild(String id, List<ReadCommentVo> list) {
//        // 子资源
//        List<ReadCommentVo> childList = new ArrayList<>();
//        for (ReadCommentVo resource : list) {
//            // 遍历所有节点，将父菜单id与传过来的id比较
//            if (!StringUtils.isBlank(resource.getReplyId())) {
//                if (resource.getReplyId().equals(id)) {
//                    childList.add(resource);
//                }
//            }
//        }
//        // 递归退出条件
//        if (childList.size() == 0) {
//            return null;
//        }
//
//        // 把子菜单的子菜单再循环一遍
//        for (ReadCommentVo subRes : childList) {// 没有url子菜单还有子菜单
//            // 递归
//            subRes.setCommList(getChild(subRes.getId(), list));
//        }
//        return childList;
//    }
}
