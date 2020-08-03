package com.trs.devopsdemo.domain.usecase;

import com.trs.devopsdemo.domain.api.Query;
import lombok.Data;

import java.util.List;

/**
 * @Title ReqData
 * @Description:
 * @Create Date: 2020/8/3 16:20
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class ReqData {

    private String uuid;
    private String title;
    private List<Query> query;
    private ReqBody body;
    private Assertion assertion;

}
