package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.pojo.ItemCat;
import com.sziit.service.ItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.controller
 *  @文件名:   ItemCatController
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/19 10:19
 *  @描述：    TODO
 */
@Controller
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;


    @RequestMapping("/rest/item/cat")
    @ResponseBody
    public List<ItemCat> selectItemCat(@RequestParam(defaultValue = "0") long id){

        List<ItemCat> list = itemCatService.selectItemCatByParentId(id);

        System.out.println("list==" + list);

        return list;
    }
}
