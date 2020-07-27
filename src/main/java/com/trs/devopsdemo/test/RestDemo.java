package com.trs.devopsdemo.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title RestDemo
 * @Description:
 * @Create Date: 2020/7/24 11:04
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public class RestDemo {

    @Test
    public void test(){

        Map form=new HashMap<>();
        form.put("username","admin");
        form.put("password","1907836146");
        Map headers=new HashMap();
        headers.put("X-User-Token","xxxx");
        headers.put("Content-Type","application/json");
        Map body=new HashMap();


        Response response = RestAssured
                .given()
                .headers(headers)
                .body("{\n" +
                "    \"groupName\":\"阿波罗devops测试环境\",\n" +
                "    \"basepath\":\"127.0.0.1/gov\"\n" +
                "}")
                .post("http://localhost:8080/devops/autotest/apiManagement/createGroup")
                .then()
                .contentType("application/json")
                .extract()
                .response();

        System.out.println("================"+response.getBody().asString());

    }

}
