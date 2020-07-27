package com.trs.devopsdemo.service;

import com.trs.devopsdemo.domain.model.DevopsAutotestApiGroup;
import com.trs.midend.result.BaseResult;

import java.util.Map;

/**
 * @Title DevopsAutotestService
 * @Description: 自动化测试接口管理业务层接口
 * @Create Date: 2020/7/20 17:13
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
public interface DevopsAutotestApiManagementService {


    BaseResult insertApiGroup(DevopsAutotestApiGroup group, Map<String, String> loginUser);
    BaseResult updateApiGroup(DevopsAutotestApiGroup group, Map<String, String> loginUser);
    BaseResult deleteApiGroupByGroupId(Integer groupId, Map<String, String> loginUser);
    BaseResult selectGroupsByUserId(Long userId);
    BaseResult selectApisByGoupId(DevopsAutotestApiGroup group, Map<String, String> loginUser);
}
