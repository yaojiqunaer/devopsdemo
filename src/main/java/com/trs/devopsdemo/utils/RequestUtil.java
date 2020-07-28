package com.trs.devopsdemo.utils;

import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class RequestUtil {


    public static Response sendpostWithSoap(String surl,Map headers,String body) throws MalformedURLException {
        URL url = new URL(surl);
        headers.put("Content-Type","text/xml");
        return given().headers(headers)
                .body(body)
                .then().when()
                .post(url);
    }

    public static Response sendpostWithHttp(String surl, Map headers, String dto) throws MalformedURLException {
        URL url = new URL(surl);
        Response response = given().
                headers(headers).
                body(dto).
                then().
                when().
                post(url);
        return response;
    }

    public static Response sendgetWithHttp(String surl, Map params, Map headers) throws MalformedURLException {
        URL url = new URL(surl);
        if (isMapEmpty(params)){//无参请求
            if(isMapEmpty(headers)){//无headers
                return given()
                        .when()
                        .get(surl);
            }
            //无参有headers
            return given()
                    .headers(headers)
                    .when()
                    .get(surl);
        }
        if(isMapEmpty(headers)){//有参数无headers
            return given()
                    .queryParams(params)
                    .when()
                    .get(surl);
        }
        return given()
                .headers(headers)
                .queryParams(params)
                .when()
                .get(surl);
    }

    public static Response sendpostWithHttps(String surl, Map headers, String body) throws Exception {
        URL url = new URL(surl);
        useRelaxedHTTPSValidation();
        Response response = given().
                headers(headers).
                body(body).
                then().
                when().
                post(url);
        response.getBody().prettyPrint();
        return response;
    }

    public static Response sendgetWithHttps(String surl,Map headers, Map params) throws MalformedURLException {
        URL url = new URL(surl);
        useRelaxedHTTPSValidation();
        Response response = given()
                .queryParams(params)
                .headers(headers)
                .when()
                .get(url);
        return response;
    }


    private static boolean isMapEmpty(Map map) {
        return Objects.isNull(map) || map.size() == 0;
    }

}