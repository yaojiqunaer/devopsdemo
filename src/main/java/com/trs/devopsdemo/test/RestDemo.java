package com.trs.devopsdemo.test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.MalformedURLException;

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
public class RestDemo {


    public static void main(String[] args) {
        //TestSeesion.get();
    }

    //@Test
    public void test() throws MalformedURLException {

//        Response response =
//                given()
//                .contentType("application/json")
//                .body("{\"tel\":\"MTM4ODg4ODg4ODM=\",\"password\":\"dHJzYWRtaW4xMjM=\",\"random\":27469}")
//                .when().post("http://apolloweb.devdemo.trs.net.cn/apollo/user/login");

        //System.out.println((String) from(response.asString()).get("resultCode.code"));
        //System.out.println("================"+response.getBody().asString());
        RequestSpecification requestSpecification=given();
        Header header = new Header("Content-Type","application/json");

        Response response = requestSpecification.body("{\\\"tel\\\":\\\"MTM4ODg4ODg4ODM=\\\"," +
                "\\\"password\\\":\\\"dHJzYWRtaW4xMjM=\\\"," +
                "\\\"random\\\":27469}").
                header(header).
                when().
                post("http://apolloweb.devdemo.trs.net.cn/apollo/user/login");

        System.out.println(response.time());

    }

}
