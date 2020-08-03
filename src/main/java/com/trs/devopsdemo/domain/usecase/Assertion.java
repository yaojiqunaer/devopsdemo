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
    private String type;

    private String source;
    private String property;
    private String comparison;
    private String targetValue;
    private char isSuccess;
    private String failedReason;

}
