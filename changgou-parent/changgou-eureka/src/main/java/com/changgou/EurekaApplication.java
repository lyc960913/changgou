package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ Author     ：CodeLiu
 * @ Date       ：Created in 2020/09/08 19:12
 * @ Description：
 * @ Modified By：
 */
@SpringBootApplication
@EnableEurekaServer //这是个eureka服务
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
