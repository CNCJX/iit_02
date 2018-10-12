package com.sziit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sziit.mapper.ContentMapper;
import com.sziit.pojo.Content;
import com.sziit.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.service.impl
 *  @文件名:   ContentServiceImpl
 *  @创建者:   Cjx
 *  @创建时间:  2018/10/10 15:20
 *  @描述：    TODO
 */
@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public int add(Content content) {

        content.setCreated(new Date());
        content.setUpdated(new Date());
        return contentMapper.insert(content);

    }

    @Override
    public PageInfo<Content> list(int categoryId, int page, int rows) {


        //设置分页
        PageHelper.startPage(page , rows);

        //查询
        Content content = new Content();


        content.setCategoryId((long) categoryId);
        List<Content> list = contentMapper.select(content);

        return new PageInfo<>(list);
    }

    @Override
    public int edit(Content content) {

        return contentMapper.updateByPrimaryKeySelective(content);

    }

    @Override
    public int delete(String ids) {

        String[] idArray = ids.split(",");

        int result = 0;
        for (String id : idArray) {

            result += contentMapper.deleteByPrimaryKey(Long.parseLong(id));

        }

        return result;
    }


}
