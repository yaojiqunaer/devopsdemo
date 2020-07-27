package com.trs.devopsdemo.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @Title ApiDto
 * @Description: ApiDto对象
 * @Create Date: 2020/7/22 17:16
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class ApiDTO {

    private Long groupId;//分组ID
    private String type;//类型 HTTP、WebService
    private String name;//API名
    private String method;//请求方式GET、POST
    private String path;//路径 /devops
    private String reqBodyType;//请求体类型 json、form
    //private String reqBody;//请求体json字符串
    private ReqBody resBody;
    private String reqBody;//json请求体
    private List<BodyForm> bodyForm;//表单参数
    private List<ReqQuery> reqQuery;//请求参数集合
    private List<ReqHeaders> reqHeaders;//请求头集合

}
