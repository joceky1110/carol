package com.carol.admin.controller;

import com.carol.admin.base.JsonResult;
import com.carol.admin.base.PageTable;
import com.carol.admin.base.QueryFilter;
import com.carol.admin.service.BaseService;
import com.carol.model.ReadLabel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 悦读标签表控制层
 * @author chris
 */
@Controller
public class ReadLabelCtrl extends BaseCtrl<ReadLabel>{

    @Resource(name="readLabelService")
    @Override
    public void setService(BaseService service) {
          super.service = service;
    }

    /**
     * 悦读标签表列表
     * @return
     */
    @RequestMapping("/page/readLabel")
    public String index() {
        return "page/readLabel/index";
    }

    /**
     * 查看单个实体信息
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/page/readLabel/info")
    public String info(String id,Model model,HttpServletRequest request ) {
        ReadLabel readLabel = ( ReadLabel)service.get(id);
        model.addAttribute("readLabel",readLabel);
        return "page/readLabel/info";
    }
    /**
     * 搜索列表
     * @param request
     * @return
     */
    @RequestMapping("/data/readLabel/list")
    @ResponseBody
    public PageTable findAll(HttpServletRequest request ) {
        QueryFilter filter = new QueryFilter(request);
        return super.find(filter);
    }

    /**
     * 添加悦读标签表页面
     * @return
     */
    @RequestMapping("/page/readLabel/add")
    public String addPage() {
        return "page/readLabel/add";
    }

    /**
     * 添加悦读标签表动作
     * @return
     */
    @RequestMapping("/data/readLabel/add")
    @ResponseBody
    public JsonResult add(ReadLabel readLabel) {
        return super.add(readLabel);
    }

    /**
     * 编辑悦读标签表页面
     * @return
     */
    @RequestMapping("/page/readLabel/edit")
    public String edit(Model model, String id) {
        ReadLabel  readLabel = (ReadLabel )service.get(id);
        model.addAttribute("readLabel",readLabel);
        return "page/readLabel/edit";
    }

    /**
     * 编辑悦读标签表动作
     * @return
     */
    @RequestMapping("/data/readLabel/edit")
    @ResponseBody
    public JsonResult update( ReadLabel readLabel, HttpServletRequest request) {
        return super.update(readLabel);
    }

    /**
     * 删除悦读标签表动作
     * @return
     */
    @RequestMapping("/data/readLabel/delete")
    @ResponseBody
    public JsonResult delete( HttpServletRequest request,Integer id) {
        QueryFilter filter = new QueryFilter(request);
        if (id != null) {
            filter.addFilter("Q_t.id_=_Long", "" + id);
        }
        return super.delete(filter);
    }
}
