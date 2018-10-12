package com.sziit.service;

import com.github.pagehelper.PageInfo;
import com.sziit.pojo.Content;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.service
 *  @文件名:   ContentService
 *  @创建者:   Cjx
 *  @创建时间:  2018/10/10 15:19
 *  @描述：    TODO
 */
public interface ContentService {

    int add(Content content);

    PageInfo<Content> list(int categoryId , int page , int rows);

    int edit(Content content);

    int delete(String ids);

}
