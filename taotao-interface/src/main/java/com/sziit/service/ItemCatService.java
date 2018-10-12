package com.sziit.service;

import com.sziit.pojo.ItemCat;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.service
 *  @文件名:   ItemCatService
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/19 10:23
 *  @描述：    TODO
 */
public interface ItemCatService {

    List<ItemCat> selectItemCatByParentId(long parentId);
}
