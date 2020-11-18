package com.my.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard//开启监控
public class DeptConmuserDashBoard_9001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConmuserDashBoard_9001.class,args);
    }
}
