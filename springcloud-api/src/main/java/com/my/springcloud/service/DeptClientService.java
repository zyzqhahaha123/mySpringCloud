package com.my.springcloud.service;


import com.my.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class) //通过指定服务名来获取服务
public interface DeptClientService {

    @PostMapping("/dept/add")
    public boolean add(Dept dept);

    @GetMapping("/dept/queryById/{deptno}")
    public Dept queryById(@PathVariable("deptno") Long deptno);

    @GetMapping("/dept/queryAll")
    public List<Dept> queryAll();

}
