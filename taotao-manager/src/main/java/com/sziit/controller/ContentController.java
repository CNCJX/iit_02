package com.sziit.controller;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.service.impl
 *  @文件名:   ContentController
 *  @创建者:   Cjx
 *  @创建时间:  2018/10/10 15:16
 *  @描述：    TODO
 */


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sziit.pojo.Content;
import com.sziit.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ContentController {

     @Reference
    private ContentService contentService;

    //@RequestMapping("/rest/content")
    @PostMapping("/rest/content")
    public String add(Content content){
        contentService.add(content);

        return "success";
    }
    //@RequestMapping("/rest/content")
    @GetMapping("/rest/content")
    public Map<String , Object> list(int categoryId , int page , int rows){

        PageInfo<Content> pageInfo = contentService.list(categoryId, page, rows);

        //EasyUi 显示列表数据的时候， 要求的数据比较严格： 要求有两个字段
        //total，rows
        Map<String , Object> map = new HashMap<>();

        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());

        return map;
    }

    @RequestMapping("/rest/content/edit")
    public Map<String , Integer> edit(Content content){

        int result = contentService.edit(content);

        Map<String , Integer> map = new HashMap<>();

        if(result > 0){
            map.put("status" , 200);
        }else{
            map.put("status" , 500);
        }

        return map;

    }

    @RequestMapping("/rest/content/delete")
    //@PostMapping("/rest/content/delete")
    public Map<String, Integer> delete(String ids){

        System.out.println("ids=" +ids);

        int result = contentService.delete(ids);

        System.out.println("result=" + result);

        Map<String , Integer> map = new HashMap<>();

        if(result > 0){
            map.put("status" , 200);
        }else{
            map.put("status" , 500);
        }

        return map;
    }
}
