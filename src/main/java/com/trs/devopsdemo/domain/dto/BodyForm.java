package com.trs.devopsdemo.domain.dto;

import lombok.Data;

/**
 * @Title BodyForm
 * @Description:
 * @Create Date: 2020/7/23 14:56
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class BodyForm {

    private String name;//表单字段名
    private String type;//字段类型
    private String required;//是否必填
}
