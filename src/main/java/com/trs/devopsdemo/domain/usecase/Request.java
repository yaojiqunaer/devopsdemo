package com.trs.devopsdemo.domain.usecase;

import lombok.Data;

import java.util.List;

/**
 * @Title Request
 * @Description:
 * @Create Date: 2020/8/3 16:19
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class Request {
    private Long apiId;
    private List<ReqData> data;


}
