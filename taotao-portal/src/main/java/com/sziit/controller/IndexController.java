package com.sziit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.controller
 *  @文件名:   IndexController
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/26 15:14
 *  @描述：    TODO
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){

        return "index";
    }

}
