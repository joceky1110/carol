package com.carol.admin.controller;

import com.carol.admin.base.JsonResult;
import com.carol.admin.base.PageTable;
import com.carol.admin.base.QueryFilter;
import com.carol.admin.service.BaseService;
import com.carol.admin.service.UserBabyService;
import com.carol.model.UserInfo;
import com.carol.vo.UserInfoVo;
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
 * 用户表控制层
 * @author chris
 */
@Controller
public class UserInfoCtrl extends BaseCtrl<UserInfo>{

    @Autowired
    private UserBabyService userBabyService;

    @Resource(name="userInfoService")
    @Override
    public void setService(BaseService service) {
          super.service = service;
    }

    /**
     * 用户表列表
     * @return
     */
    @RequestMapping("/page/userInfo")
    public String index() {
        return "page/userInfo/index";
    }

    /**
     * 查看单个患者信息
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/page/userInfo/info")
    public String info(String id,Model model,HttpServletRequest request ) {
        UserInfo userInfo = ( UserInfo)service.get(id);
        model.addAttribute("userInfo",userInfo);
        return "page/userInfo/info";
    }

    /**
     * 搜索列表
     * @param request
     * @return
     */
    @RequestMapping("/data/userInfo/list")
    @ResponseBody
    public PageTable findAll(HttpServletRequest request ) {
        QueryFilter filter = new QueryFilter(request);
        PageTable pageTable = super.find(filter);

        List<UserInfo> userInfos =  pageTable.getData();
        List<UserInfoVo> list = new ArrayList<>();
        for(UserInfo info : userInfos){
            UserInfoVo vo = new UserInfoVo();
            BeanUtils.copyProperties(info,vo);
            vo.setBabies(userBabyService.listByParentId(vo.getId()));
            list.add(vo);
        }
        pageTable.setData(list);
        return pageTable;
    }

    /**
     * 添加用户表页面
     * @return
     */
    @RequestMapping("/page/userInfo/add")
    public String addPage() {
        return "page/userInfo/add";
    }

    /**
     * 添加用户表动作
     * @return
     */
    @RequestMapping("/data/userInfo/add")
    @ResponseBody
    public JsonResult add(UserInfo userInfo) {
        return super.add(userInfo);
    }

    /**
     * 编辑用户表页面
     * @return
     */
    @RequestMapping("/page/userInfo/edit")
    public String edit(Model model, String id) {
        UserInfo  userInfo = (UserInfo )service.get(id);
        model.addAttribute("userInfo",userInfo);
        return "page/userInfo/edit";
    }

    /**
     * 编辑用户表动作
     * @return
     */
    @PostMapping("/data/userInfo/edit")
    @ResponseBody
    public JsonResult update( UserInfo userInfo, HttpServletRequest request) {
        return super.update(userInfo);
    }

    /**
     * 删除用户表动作
     * @return
     */
    @RequestMapping("/data/userInfo/delete")
    @ResponseBody
    public JsonResult delete( HttpServletRequest request,Integer id) {
        QueryFilter filter = new QueryFilter(request);
        if (id != null) {
            filter.addFilter("Q_t.id_=_String", "" + id);
        }
        return super.delete(filter);
    }
}
