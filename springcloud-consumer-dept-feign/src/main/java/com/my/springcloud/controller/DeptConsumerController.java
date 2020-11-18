package com.my.springcloud.controller;


import com.my.springcloud.pojo.Dept;
import com.my.springcloud.service.DeptClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


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
    private DeptClientService service = null;


    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return this.service.add(dept);
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
        return this.service.queryById(deptno);
    }

    @RequestMapping("/consumer/dept/queryAll")
    public List<Dept> queryAll(){
        return this.service.queryAll();
    }


}
