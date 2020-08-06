package com.trs.devopsdemo.domain.dto;

import lombok.Data;

/**
 * @Title ApiGroupDto
 * @Description: 接口分组DTO对象
 * @Create Date: 2020/8/6 17:07
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class ApiGroupDto {

    private Long groupId;
    private String groupName;//分组名
    private String basepath;//基本路径


}
