package com.trs.devopsdemo.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * @Title ResBodyField
 * @Description:
 * @Create Date: 2020/8/18 18:27
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class ResBodyField {

    private String parameterName;
    private Integer dataType;
    private List<ResBodyField> child;

}
