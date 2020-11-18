package com.my.springcloud.controller;


import com.my.springcloud.pojo.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
public class DeptConsumerController {


    /*
    * 理解：消费者 不应该有service 层
    * 这里没有去引用 生产者 的信息，那么我们怎么才能去 获得 service层呢，
    * 没有service层，但是要调用远程的服务
    * service可以通过请求去获取，那么怎么去获取 请求的
    * SpringBoot支持 Restful风格请求 通过对RestTemplate 来获取
    * （url,实体：map，Class<T> responseType） 三个关键参数
    * */
    @Autowired
    private RestTemplate restTemplate;// 提供多种边界访问远程 http服务的方法，简单的Restful服务模板

    //Ribbon 我们这里的地址，应该是一个变量，通过服务名来访问
    //这里因为没有对应的service层，所以通过restTemplate去调用,用http的 url 方式去调用
//    private static final String REST_FUL_PREFIX = "http://localhost:8001"; //这个生产者中的请求前缀都是一样的
    private static final String REST_FUL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";


    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        System.out.println(dept);
        return restTemplate.postForObject(REST_FUL_PREFIX + "/dept/add",dept,Boolean.class);
    }

    /*
    * 传参的方式有三种：
    *   1.通过map 方式传参
    *   2.通过接在 url 后面直接传参
    *   3.直接把他当作一个实体来进行传参
    *
    * */
    @RequestMapping("/consumer/dept/queryById/{deptno}")
    public Dept queryById(@PathVariable("deptno") Long deptno){
        System.out.println("为什么走了它");
        return restTemplate.getForObject(REST_FUL_PREFIX + "/dept/queryById/" + deptno,Dept.class);//写法1 get请求
        //return restTemplate.getForObject(REST_FUL_PREFIX + "/dept/queryById/",Dept.class,deptno); 写法2
    }


    @RequestMapping("/consumer/dept/queryAll")
    public List<Dept> queryAll(){
        System.out.println("走了这里");
        return restTemplate.getForObject(REST_FUL_PREFIX + "/dept/queryAll", List.class);
    }


}
