package com.sziit.service;

import com.sziit.pojo.ContentCategory;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.service
 *  @文件名:   ContentCategoryService
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/28 13:25
 *  @描述：    TODO
 */
public interface ContentCategoryService {

    List<ContentCategory> getCategoryByParentId(Long id);

    ContentCategory add(ContentCategory contentCategory);

    int update(ContentCategory contentCategory);

    int delete(ContentCategory contentCategory);
}
