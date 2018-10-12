package com.sziit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit
 *  @文件名:   ManagerApp
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/11 11:04
 *  @描述：    TODO
 */

//告诉SpringBoot不要检测数据源 exclude: 不要包含数据源的配置
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)

@SpringBootApplication
public class ManagerApp {
    public static void main(String [] args){
        SpringApplication.run(ManagerApp.class,args);
    }
}
