package com.trs.devopsdemo.test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.MalformedURLException;

import static io.restassured.RestAssured.given;

/**
 * @Title TestSeesion
 * @Description:
 * @Create Date: 2020/8/4 15:48
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public class TestSeesion {

   public  static void get(){
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


    public static void main(String[] args) throws MalformedURLException {


        RequestSpecification requestSpecification=given();
        Header header = new Header("Content-Type","application/json");

        Response response = requestSpecification.body("{\\\"tel\\\":\\\"MTM4ODg4ODg4ODM=\\\"," +
                "\\\"password\\\":\\\"dHJzYWRtaW4xMjM=\\\"," +
                "\\\"random\\\":27469}").
                header(header).
                when().
                post("http://apolloweb.devdemo.trs.net.cn/apollo/user/login");

        System.out.println(response.time());
        Response response2 = requestSpecification.body("{\\\"tel\\\":\\\"MTM4ODg4ODg4ODM=\\\"," +
                "\\\"password\\\":\\\"dHJzYWRtaW4xMjM=\\\"," +
                "\\\"random\\\":27469}").
                header(header).
                when().
                post("http://apolloweb.devdemo.trs.net.cn/apollo/user/login");
        System.out.println(response2.time());


    }


}
