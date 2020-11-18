package com.my.springcloud.controller;


import com.my.springcloud.pojo.Dept;
import com.my.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


//提供Restful服务
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;



    @GetMapping("/dept/queryById/{deptno}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("deptno") Long deptno){
        System.out.println("走了这里");
        Dept dept = deptService.queryById(deptno);
        if (dept == null){
            throw new RuntimeException("deptNo=>" + deptno + ",不存在该用户信息，或信息无法找到~~~~");
        }
        return dept;
    }

    //备选方法
    public Dept hystrixGet(@PathVariable("deptno") Long deptno){
        return new Dept("deptNo=>" + deptno + ",不存在该用户信息，或信息无法找到~~~~");

//        return new Dept("deptNo=>" + deptNo + ",不存在该用户信息，或信息无法找到~~~~");

//        return new Dept().setDeptno(deptNo)("deptNo=>" + deptNo + ",不存在该用户信息，或信息无法找到~~~~")
//                .setDb_source("no this database in MySql");
    }



}
