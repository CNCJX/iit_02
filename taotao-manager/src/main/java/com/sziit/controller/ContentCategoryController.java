package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.pojo.ContentCategory;
import com.sziit.service.ContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.controller
 *  @文件名:   ContentCategoryController
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/28 13:14
 *  @描述：    TODO
 */
@Controller
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/rest/content/category")
    @ResponseBody
    public List<ContentCategory> getCategoryByParentId(@RequestParam(defaultValue = "0") Long id){

        List<ContentCategory> list = contentCategoryService.getCategoryByParentId(id);

        return list;
    }

    @RequestMapping("/rest/content/category/add")
    @ResponseBody
    public ContentCategory add(ContentCategory contentCategory){

        contentCategory = contentCategoryService.add(contentCategory);

        return contentCategory;
    }

    @RequestMapping("/rest/content/category/update")
    @ResponseBody
    public String update(ContentCategory contentCategory){ //传进来的数据是 id，name

        contentCategoryService.update(contentCategory);

        return "success";
    }

    @RequestMapping("/rest/content/category/delete")
    @ResponseBody
    public String delete(ContentCategory contentCategory){ //id , parentId


        int result = contentCategoryService.delete(contentCategory);

        System.out.println("result==" + result);


        return "success";
    }
}
