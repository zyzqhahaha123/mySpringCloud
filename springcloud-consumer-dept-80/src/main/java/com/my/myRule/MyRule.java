package com.my.myRule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRule {

    @Bean
    public IRule getMyRule(){
        return new MyRandomRule(); // 默认是轮询，现在我们定义为：MyRandomRule
    }
}



