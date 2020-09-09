package com.changgou.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

import javax.swing.*;

/**
 * @ Author     ：CodeLiu
 * @ Date       ：Created in 2020/09/08 20:05
 * @ Description：
 * @ Modified By：
 */
@SpringBootApplication
@EnableEurekaClient//开启eureka客户端
@MapperScan(basePackages = {"com.changgou.goods.dao"})
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);
    }
}
