package com.sziit.service;


import com.github.pagehelper.PageInfo;
import com.sziit.pojo.User;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.service
 *  @文件名:   UserService
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/11 16:47
 *  @描述：    TODO
 */
public interface UserService {

    void save();

    User selectOne(long id);

    PageInfo<User> findByPage(int currentPage, int pageSize);
}
