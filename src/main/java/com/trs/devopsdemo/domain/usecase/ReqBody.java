package com.trs.devopsdemo.domain.usecase;

import lombok.Data;

/**
 * @Title ReqBody
 * @Description: 接口请求的body数据
 * @Create Date: 2020/8/3 16:23
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class ReqBody {

    private Integer type;//0为常量，1为接口返回
    private String value;//json字符串 "value": "{\"username\":\"韩信\",\"password\":\"123456\"}"

}
