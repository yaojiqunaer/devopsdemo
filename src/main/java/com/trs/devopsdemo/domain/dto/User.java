package com.trs.devopsdemo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title User
 * @Description:
 * @Create Date: 2020/8/3 17:02
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String sex;
    private Integer age;

}
