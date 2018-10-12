package com.sziit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sziit.mapper.UserMapper;
import com.sziit.pojo.User;
import com.sziit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itheima.service.impl
 *  @文件名:   UserServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/11 11:06
 *  @描述：    TODO
 */

//@Service

@Service  //请一定要使用dubbo的这个@Service注解
public class UserServiceImpl implements UserService {

    //要想在service里面使用Mapper，那么service模块必须依赖mapper的模块


    @Autowired
    private UserMapper userMapper;

    @Override
    public void save() {
        System.out.println("调用了UserServiceImpl的save方法~！");
    }

    @Override
    public User selectOne(long id) {

        // Long id2 = 7L;
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<User> findByPage(int currentPage, int pageSize) {


        PageHelper.startPage(currentPage , pageSize);

        List<User> list = userMapper.selectAll();

        return new PageInfo<>(list);
    }
}
