package com.trs.devopsdemo.domain.api;

import lombok.Data;

/**
 * @Title ReqHeaders
 * @Description: 请求头DTO对象
 * @Create Date: 2020/7/22 17:20
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class Header {

    private String name;
    private String value;
    private String example;
    private String description;

    private Integer type = 0;//0为常量 1为接口返回
}
