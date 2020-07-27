package com.trs.devopsdemo.domain.dto;

import lombok.Data;

/**
 * @Title TypeAndDes
 * @Description:
 * @Create Date: 2020/7/24 17:03
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class TypeAndDes {

    private String type;
    private String description;
    private Items items;

}
