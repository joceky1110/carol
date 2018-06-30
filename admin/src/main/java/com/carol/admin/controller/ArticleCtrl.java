package com.carol.admin.controller;

import com.carol.admin.base.JsonResult;
import com.carol.admin.base.PageTable;
import com.carol.admin.base.QueryFilter;
import com.carol.admin.service.BaseService;
import com.carol.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 专栏文章表控制层
 * @author chris
 */
@Controller
public class ArticleCtrl extends BaseCtrl<Article>{

    @Resource(name="articleService")
    @Override
    public void setService(BaseService service) {
          super.service = service;
    }

    /**
     * 专栏文章表列表
     * @return
     */
    @RequestMapping("/page/article")
    public String index() {
        return "page/article/index";
    }

    /**
     * 查看单个实体信息
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/page/article/info")
    public String info(String id,Model model,HttpServletRequest request ) {
        Article article = ( Article)service.get(id);
        model.addAttribute("article",article);
        return "page/article/info";
    }
    /**
     * 搜索列表
     * @param request
     * @return
     */
    @RequestMapping("/data/article/list")
    @ResponseBody
    public PageTable findAll(HttpServletRequest request ) {
        QueryFilter filter = new QueryFilter(request);
        return super.find(filter);
    }

    /**
     * 添加专栏文章表页面
     * @return
     */
    @RequestMapping("/page/article/add")
    public String addPage() {
        return "page/article/add";
    }

    /**
     * 添加专栏文章表动作
     * @return
     */
    @RequestMapping("/data/article/add")
    @ResponseBody
    public JsonResult add(Article article) {
        article.setUserId("1234567890");
        article.setUserName("毛妈caro");//todo:用户
        return super.add(article);
    }

    /**
     * 编辑专栏文章表页面
     * @return
     */
    @RequestMapping("/page/article/edit")
    public String edit(Model model, String id) {
        Article  article = (Article )service.get(id);
        model.addAttribute("article",article);
        return "page/article/edit";
    }

    /**
     * 编辑专栏文章表动作
     * @return
     */
    @RequestMapping("/data/article/edit")
    @ResponseBody
    public JsonResult update( Article article, HttpServletRequest request) {
        return super.update(article);
    }

    /**
     * 删除专栏文章表动作
     * @return
     */
    @RequestMapping("/data/article/delete")
    @ResponseBody
    public JsonResult delete( HttpServletRequest request,Integer id) {
        QueryFilter filter = new QueryFilter(request);
        if (id != null) {
            filter.addFilter("Q_t.id_=_Long", "" + id);
        }
        return super.delete(filter);
    }
}
