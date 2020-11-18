package com.my.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


//Ribbon 和 Eureka 整合以后，客户可以直接调用，不用关心 ip地址 和 端口号
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients( basePackages = {"com.my.springcloud"})
@ComponentScan("com.my.springcloud")
public class FeignDeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignDeptConsumer_80.class,args);
    }

}
