package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sziit.pojo.Item;
import com.sziit.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.controller
 *  @文件名:   ItemController
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/19 15:28
 *  @描述：    TODO
 */
@Controller
public class ItemController{

    @Reference
    private ItemService itemService;


    @RequestMapping(value = "/rest/item",method = RequestMethod.POST)
    @ResponseBody
    public String addItem(Item item, String desc){


        int result = itemService.addItem(item,desc);

        System.out.println("result===" + result);

        return "success";
    }

    @RequestMapping(value = "/rest/item" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String , Object> list(int page, int rows){

        PageInfo<Item> pageInfo = itemService.list(page , rows);


        Map<String , Object> map = new HashMap<>();
        map.put("total" , pageInfo.getTotal());
        map.put("rows" , pageInfo.getList());

        return map;

    }
}
