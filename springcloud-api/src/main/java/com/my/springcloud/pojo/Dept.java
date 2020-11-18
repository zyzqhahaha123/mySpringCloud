package com.my.springcloud.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@NoArgsConstructor
@Accessors(chain = true)    //开启 链式写法
public class Dept implements Serializable {//Dept 实体类 orm 类表关系映射 这里要实现序列化，不然打包的时候可能会报错

    private Long deptno;//主键

    private String dname;

    //这个数据存在哪个数据库的字段~~  微服务：一个服务对应一个数据库，同一个信息可能存在不同的数据库中
    private String db_source;


    public Dept(String dname) {
        this.dname = dname;
    }



    /*
    Dept dept = new Dept();

    普通写法：dept.setDeptNo(11);   这样一个个的set
             dept.setDname('sss');

    链式写法：dept.setDeptNo(11).setDname('sss').setDb_source('001');  直接在后面接着 . 出来就可以了



    * */


}
