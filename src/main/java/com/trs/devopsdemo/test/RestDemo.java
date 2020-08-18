package com.trs.devopsdemo.test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

/**
 * @Title RestDemo
 * @Description:
 * @Create Date: 2020/7/24 11:04
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Slf4j
public class RestDemo {


    public static void main(String[] args) {
        RequestSpecification requestSpecification=given();
        Header header = new Header("Content-Type","application/json");

        Response response = requestSpecification.body("{\"tel\":\"MTM4ODg4ODg4ODM=\"," +
                "\"password\":\"dHJzYWRtaW4xMjM=\",\"random\":27469}").
                header(header).
                when().
                post("http://apolloweb.devdemo.trs.net.cn/apollo/user/login");
        System.out.println(response.asString());
        ValidatableResponse validatableResponse = response.then();//断言
        Matcher<String> tokenMatcher = Matchers.equalTo("87e2cacc4d434494b686c293ee015dc9");//等于的断言
        Matcher<String> realName = Matchers.equalTo("管理员");//等于的断言
        Matcher<String> realName2 = Matchers.containsString("管理");
        try {
            validatableResponse.body("xxx.data[0]",tokenMatcher);//断言失败
        }catch (AssertionError e){
            //报告打印
            log.info("断言失败{}",e.getMessage());
        }catch (Exception e){
            log.info("error");
        }

        System.out.println("=====================");
        validatableResponse.body("result.data[0].realName",realName);//断言body成功无异常
        validatableResponse.body("result.data[0].realName",realName2);//断言成功无异常

    }

}
