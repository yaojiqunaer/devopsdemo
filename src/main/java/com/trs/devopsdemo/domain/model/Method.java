package com.trs.devopsdemo.domain.model;

/**
 * @ClassName Method
 * @Description: 请求方法枚举类
 * @Author zhangxiaodong
 * @Date 2020/7/8 16:13
 * @Version V1.0
 */
public enum Method {

    GET("GET"),
    POST("POST"),
    DELETE("DELETE"),
    PUT("PUT"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    PATCH("PATCH");


    private String name;

    private Method(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

}
