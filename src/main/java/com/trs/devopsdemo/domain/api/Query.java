package com.trs.devopsdemo.domain.api;

import lombok.Data;

/**
 * @Title ReqQuery
 * @Description:
 * @Create Date: 2020/7/22 17:19
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class Query {

    private String name;
    private String type;
    private String example;
    private String description;

    private String value;

}
