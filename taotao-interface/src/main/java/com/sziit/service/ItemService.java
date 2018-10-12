package com.sziit.service;

import com.github.pagehelper.PageInfo;
import com.sziit.pojo.Item;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.service
 *  @文件名:   ItemService
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/19 15:31
 *  @描述：    TODO
 */
public interface ItemService {

    int addItem(Item item,String desc);

    PageInfo<Item> list(int page , int rows);
}
