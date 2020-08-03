package com.trs.devopsdemo.enums;

/**
 * @Title AssertType
 * @Description: 断言类型
 * @Create Date: 2020/8/3 16:31
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public enum AssertType {
    INCLUDE("包含"),
    EQUALS("等于");

    private final String name;

    private AssertType(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }
}
