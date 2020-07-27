package com.trs.devopsdemo.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @Title Items
 * @Description:
 * @Create Date: 2020/7/24 17:14
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class Items {


    private String type;
    private List required;
}
