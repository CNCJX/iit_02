package com.sziit.controller;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.controller
 *  @文件名:   PageController
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/18 18:51
 *  @描述：    TODO
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/rest/page/{pageName}")
    public String page(@PathVariable String pageName){

        System.out.println("pageName=" + pageName);

        return pageName;
    }

    @RequestMapping("/")
    public String inde(){

        return "index";
    }
}
