package com.sziit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sziit.mapper.ItemDescMapper;
import com.sziit.mapper.ItemMapper;
import com.sziit.pojo.Item;
import com.sziit.pojo.ItemDesc;
import com.sziit.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.service.impl
 *  @文件名:   ItemServiceImpl
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/19 15:30
 *  @描述：    TODO
 */
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public int addItem(Item item, String desc) {
        long id = (long) (System.currentTimeMillis() + Math.random() * 10000);
        item.setId(id);
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(new Date());

        int result = itemMapper.insertSelective(item);


        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insertSelective(itemDesc);

        return result;
    }

    @Override
    public PageInfo<Item> list(int page, int rows) {

        PageHelper.startPage(page , rows);

        List<Item> list = itemMapper.select(null);
        return new PageInfo<Item>(list);
    }
}
