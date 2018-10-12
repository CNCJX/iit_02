package com.sziit.service.impl;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.service.impl
 *  @文件名:   ItemCatServiceImpl
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/19 10:24
 *  @描述：    TODO
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.sziit.mapper.ItemCatMapper;
import com.sziit.pojo.ItemCat;
import com.sziit.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class ItemCatServiceImpl implements ItemCatService{




    @Autowired
    private ItemCatMapper itemCatMapper;
    @Override
    public List<ItemCat> selectItemCatByParentId(long parentId) {

        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);

        return itemCatMapper.select(itemCat);
    }
}
