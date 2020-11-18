package com.my.springcloud.config;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {//@Configuration 相当于 spring 中的 applicationContext.xml 来注册Bean的

    //方式一
//    @Bean
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }

    /**
     * IRule 接口
     *  实现接口：
     *      RoundRobinRule 轮询   默认是轮询
     *      RandomRule  随机
     *      AvailabilityFilteringRule  会先过滤、跳闸、访问故障的服务~~~，对剩下的进行轮询
     *      RetryRule   会先按照轮询获取服务~~~，如果服务获取失败，则会在指定的时间内进行 重试
     *
      */

    //配置负载均衡实现RestTemplate
    @Bean
    @LoadBalanced //Ribbon  开启负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        return new RandomRule();
    }

}
