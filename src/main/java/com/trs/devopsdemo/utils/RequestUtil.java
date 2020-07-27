package com.trs.devopsdemo.utils;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.net.URL;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class RequestUtil {

    public static Response sendpostWithHttp(String surl,Map headers, String dto) throws Exception {
        String msg = null;
        URL url = new URL(surl);
        Response response = given().log().all().
                headers(headers).
                body(dto).
                then().
                when().
                post(url);
        response.getBody().prettyPrint();

        return response;
    }

    public static ValidatableResponse sendgetWithHttp(String surl, Map params,Map headers) throws Exception {
        URL url = new URL(surl);
        ValidatableResponse response = null;
        if (Objects.isNull(params) || params.size() == 0) {
            //无参数
            if(Objects.isNull(headers)||headers.size()==0){
                //无headers
                response = given()
                        .when() .log().all()
                        .get(surl)
                        .then()
                        .log().all();
            }else {
                response = given()
                        .headers(headers)
                        .log().all()
                        .when()
                        .get(surl)
                        .then()
                        .log().all();
            }
        } else {
            //有参数
            if(Objects.isNull(headers)||headers.size()==0){
                response = given()
                        .log().all()
                        .queryParams(params)
                        .when()
                        .get(surl)
                        .then()
                        .log().all();
            }else {
                response = given()
                        .headers(headers)
                        .log().all()
                        .queryParams(params)
                        .when()
                        .get(surl)
                        .then()
                        .log().all();
            }

        }
        return response;
    }

    public static Response sendpostWithHttps(String surl,Map headers, Map body) throws Exception {
        URL url = new URL(surl);
        useRelaxedHTTPSValidation();
        Response response = given().
                log().all().
                headers(headers).
                body(body).
                then().
                when().
                post(url);
        response.getBody().prettyPrint();
        return response;
    }

    public static ValidatableResponse sendgetWithHttps(String surl, String str) throws Exception {
        URL url = new URL(surl);
        useRelaxedHTTPSValidation();
        ValidatableResponse response = given()
                .log().all()
                .queryParam(str)
                .when()
                .get(surl)
                .then()
                .log().all()
                .statusCode(200);
        return response;
    }
}