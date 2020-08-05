package com.trs.devopsdemo.domain.usecase;

import lombok.Data;

/**
 * @Title Assertion
 * @Description: 断言
 * @Create Date: 2020/7/31 16:49
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */

@Data
public class Assertion {

    private String name;
    private String value;
    private Integer type;//0为等于 1为包含 目前支持0,1

    private String source = "json";//默认json 目前只支持json
    //private String property;
    //private String comparison;
    //private String targetValue;
    private char isSuccess;//1为成功 0为失败
    private String failedReason;

}
