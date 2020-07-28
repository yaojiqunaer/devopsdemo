package com.trs.devopsdemo.utils;

import io.restassured.response.Response;

/**
 * @Title AssertionUtil
 * @Description:
 * @Create Date: 2020/7/28 10:48
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public class AssertionUtil {



    public static boolean jsonAssertion (Response response){

        response.prettyPrint();
        return true;
    }
}
