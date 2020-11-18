package com.my.springcloud.controller;


import com.my.springcloud.pojo.Dept;
import com.my.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//提供Restful服务
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //获取一些配置信息，得到具体的微服务！
    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
        System.out.println(dept);
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/queryById/{deptno}")
    public Dept queryById(@PathVariable("deptno") Long deptno){
        /*
        * 通过@PathVarivable 来传参的时候，前面不需要写对应 deptno=1 这种，
        * 只需要 /dept/queryById/1   @PathVariable 会自动在后台把这个1跟deptno对应起来
        * 只要要GetMapping 这里写好对应的参数名 {deptno} 这种
        * */
        System.out.println(deptno);
        return deptService.queryById(deptno);
    }

    @GetMapping("/dept/queryAll")
    public List<Dept> queryAll(){
        System.out.println("走进了这个服务");
        return deptService.queryAll();
    }


    //注册进来的微服务~~~  来获取一些消息
    @GetMapping("dept/discovery")
    public Object discovery(){
        //获取服务列表的清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery=>services" + services);

        //通过服务id applicatoinName  来获取一个具体的微服务信息
        List<ServiceInstance> list = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        list.stream().forEach(serviceInstance -> {
            System.out.println("111");
            System.out.println(serviceInstance.getHost());
            System.out.println(serviceInstance.getPort());
            System.out.println(serviceInstance.getUri());
            System.out.println(serviceInstance.getServiceId());
        });


        return this.discoveryClient;
    }

}
