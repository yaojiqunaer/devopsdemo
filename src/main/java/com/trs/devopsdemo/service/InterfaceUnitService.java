package com.trs.devopsdemo.service;

import com.alibaba.fastjson.JSON;
import com.trs.devopsdemo.entity.JsonBean;

/**
 * @Title InterfaceUnitService
 * @Description: 接口业务逻辑单元
 * @Create Date: 2020/7/16 14:35
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public interface InterfaceUnitService {

    JsonBean yapiSmart(JSON json);

    JsonBean swaggerSmart(JSON json);

    JsonBean postmanSmart(JSON json);

    JsonBean yapiNormal(JSON json);

    JsonBean swaggerNormal(JSON json);

    JsonBean postmanNormal(JSON json);


}
