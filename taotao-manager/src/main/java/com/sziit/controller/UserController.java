package com.sziit.controller;

import com.sziit.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.controller
 *  @文件名:   UserController
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/11 11:04
 *  @描述：    TODO
 */
@RestController     // = @Controller + @ResponseBody(表示方法的返回值都是json字符串)
//@Controller
public class UserController {

    //这个注解的意思是： 自动注入，在当前这个项目的Spring容器里面寻找UserService的实现类对象，
    // 找到之后，给这个 userSerivice 赋值进来，userSerivice的实现类是UserSeriviceImpl
    //@Autowired

    private UserService userService;

    @RequestMapping("save")
    public String save(){

        System.out.println("调用了UserController的save方法~！");

        userService.save();
        return "save success~!";

    }
}
