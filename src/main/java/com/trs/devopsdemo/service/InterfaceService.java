package com.trs.devopsdemo.service;

import com.alibaba.fastjson.JSON;
import com.trs.devopsdemo.entity.JsonBean;


/**
 * @ClassName ApiInterface
 * @Description:
 * @Author zhangxiaodong
 * @Date 2020/7/8 15:12
 * @Version V1.0
 */
public interface InterfaceService {

    /**
     * @description
     * @param json api的json数据
     * @param type json类型（yapi、swagger、postman）
     * @param mode 导入模式（smart、normal）
     *             smart:智能合并
     *             normal:常规合并
     * @return
     */
    JsonBean saveApis(JSON json, String type, String mode);


}
