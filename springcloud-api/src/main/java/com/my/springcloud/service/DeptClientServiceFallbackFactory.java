package com.my.springcloud.service;


import com.my.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

//服务降级
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {

    @Override
    public DeptClientService create(Throwable cause) {//这样相当于关闭一个整体的服务
        return new DeptClientService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept queryById(Long deptno) {
                return new Dept("id=>" + "没有对应的信息，客户端提供了降级的信息，这个服务现在已经被关闭");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }
        };
    }
}
