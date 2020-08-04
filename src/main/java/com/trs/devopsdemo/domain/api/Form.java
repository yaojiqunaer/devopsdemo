package com.trs.devopsdemo.domain.api;

import lombok.Data;

/**
 * @Title Form
 * @Description:
 * @Create Date: 2020/7/31 15:41
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class Form {

    private String name;
    private Integer type;
    private String example;
    private String required;

    private String value;

}
