package com.trs.devopsdemo.utils;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title TestNGUtil
 * @Description:
 * @Create Date: 2020/7/30 15:16
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public class TestNGUtil {

    @Test(dataProvider = "datas")
    public void test(String method, String url, String body, Map headers) {

        if ("GET".equals(method)) {

        }
        if ("POST".equals(method)) {
            try {
                Response response = RequestUtil.sendpostWithHttp(url, headers, body);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @DataProvider
    public Object[][] datas() {
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        Map map4 = new HashMap();
        Map map5 = new HashMap();
        map1.put("Content-Type", "application/json");
        map1.put("X-User-Token", "d3j3k46n5676x8a");
        map2.put("Content-Type", "application/json");
        map2.put("X-User-Token", "d3j3k46n5676x8a");
        map3.put("Content-Type", "application/json");
        map3.put("X-User-Token", "d3j3k46n5676x8a");
        map4.put("Content-Type", "application/json");
        map4.put("X-User-Token", "d3j3k46n5676x8a");
        map5.put("Content-Type", "application/json");
        map5.put("X-User-Token", "d3j3k46n5676x8a");
        Object[][] datas = {
                {"POST", "http://localhost:8080/devops/test/httpGet", "{\"title\":\"你好1\",\"num\":\"10\"}",map1},
                {"POST", "http://localhost:8080/devops/test/httpGet", "{\"title\":\"你好2\",\"num\":\"10\"}",map2},
                {"POST", "http://localhost:8080/devops/test/httpGet", "{\"title\":\"你好3\",\"num\":\"10\"}",map3},
                {"POST", "http://localhost:8080/devops/test/httpGet", "{\"title\":\"你好4\",\"num\":\"10\"}",map4},
                {"POST", "http://localhost:8080/devops/test/httpGet", "{\"title\":\"你好5\",\"num\":\"10\"}",map5}
        };
        return datas;
    }


}
