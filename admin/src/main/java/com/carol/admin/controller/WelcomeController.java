package com.carol.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图管理器
 * @author YI
 * @date 2018-4-16 09:41:44*/


@Controller
public class WelcomeController {
  /*  @Autowired
    private UserService userService;*/

/**
     * 首页
     * @return*/


    @RequestMapping("/")
    public String index(){
        return "index";
    }



    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

}
