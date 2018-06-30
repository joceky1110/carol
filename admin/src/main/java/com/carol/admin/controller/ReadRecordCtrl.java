package com.carol.admin.controller;

import com.carol.admin.base.JsonResult;
import com.carol.admin.base.PageTable;
import com.carol.admin.base.QueryFilter;
import com.carol.admin.service.BaseService;
import com.carol.admin.service.UserInfoService;
import com.carol.model.ReadRecord;
import com.carol.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 悦读记录表控制层
 * @author chris
 */
@Controller
public class ReadRecordCtrl extends BaseCtrl<ReadRecord>{

    @Resource(name="readRecordService")
    @Override
    public void setService(BaseService service) {
          super.service = service;
    }

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 悦读记录表列表
     * @return
     */
    @RequestMapping("/page/readRecord")
    public String index() {
        return "page/readRecord/index";
    }

    /**
     * 查看单个实体信息
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/page/readRecord/info")
    public String info(String id,Model model,HttpServletRequest request ) {
        ReadRecord readRecord = ( ReadRecord)service.get(id);
        model.addAttribute("readRecord",readRecord);
        return "page/readRecord/info";
    }
    /**
     * 搜索列表
     * @param request
     * @return
     */
    @RequestMapping("/data/readRecord/list")
    @ResponseBody
    public PageTable findAll(HttpServletRequest request ) {
        QueryFilter filter = new QueryFilter(request);
        return super.find(filter);
    }

    /**
     * 添加悦读记录表页面
     * @return
     */
    @RequestMapping("/page/readRecord/add")
    public String addPage() {
        return "page/readRecord/add";
    }

    /**
     * 添加悦读记录表动作
     * @return
     */
    @RequestMapping("/data/readRecord/add")
    @ResponseBody
    public JsonResult add(ReadRecord readRecord) {
        return super.add(readRecord);
    }

    /**
     * 编辑悦读记录表页面
     * @return
     */
    @RequestMapping("/page/readRecord/edit")
    public String edit(Model model, String id) {
        ReadRecord  readRecord = (ReadRecord )service.get(id);
        Object obj = userInfoService.get(readRecord.getUserId());
        model.addAttribute("readRecord",readRecord);
        model.addAttribute("userInfo",obj);
        return "page/readRecord/edit";
    }

    /**
     * 编辑悦读记录表动作
     * @return
     */
    @RequestMapping("/data/readRecord/edit")
    @ResponseBody
    public JsonResult update( ReadRecord readRecord, HttpServletRequest request) {
        readRecord.setUpdateTime(new Date());
        return super.update(readRecord);
    }

    /**
     * 删除悦读记录表动作
     * @return
     */
    @RequestMapping("/data/readRecord/delete")
    @ResponseBody
    public JsonResult delete( HttpServletRequest request,Integer id) {
        QueryFilter filter = new QueryFilter(request);
        if (id != null) {
            filter.addFilter("Q_t.id_=_Long", "" + id);
        }
        return super.delete(filter);
    }
}
