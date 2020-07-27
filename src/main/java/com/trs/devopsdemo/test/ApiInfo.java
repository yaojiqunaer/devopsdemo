package com.trs.devopsdemo.test;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Title ApiInfo
 * @Description:
 * @Create Date: 2020/7/22 10:51
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class ApiInfo {

    private String index;
    private String name;
    private String desc;
    private Date add_time;
    @JSONField(name = "up_time")
    private Date upTime;
    private List<ApiList> list;

//    @JSONField
//    private Date add_time;
//    @JSONField
//    private Date up_time;

}
