package com.trs.devopsdemo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title Token
 * @Description:
 * @Create Date: 2020/8/3 16:50
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {


    private String token;

    public static String createTokenByUsernamePassword(String username, String password) {

        return username + password;

    }

}
