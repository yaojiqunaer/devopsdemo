package com.trs.devopsdemo.service.impl;

import com.trs.devopsdemo.domain.mapper.DevopsAutotestApiGroupMapper;
import com.trs.devopsdemo.domain.model.DevopsAutotestApiGroup;
import com.trs.devopsdemo.service.DevopsAutotestApiManagementService;
import com.trs.midend.result.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Title DevopsAutotestServiceImpl
 * @Description: 自动化测试接口管理服务实现类
 * @Create Date: 2020/7/20 17:14
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Slf4j
@Service
public class DevopsAutotestApiManagementServiceImpl implements DevopsAutotestApiManagementService {


    @Autowired
    private DevopsAutotestApiGroupMapper devopsAutotestApiGroupMapper;


    /**
     * @param group
     * @param loginUser
     * @return
     * @description 新增接口分组
     */
    //@Transactional
    @Override
    public BaseResult insertApiGroup(DevopsAutotestApiGroup group, Map<String, String> loginUser) {
        if (Objects.isNull(group) || StringUtils.isEmpty(group.getGroupName()) || StringUtils.isEmpty(group.getBasepath())) {
            return BaseResult.paramerr();//参数异常
        }
        group.setCreateUserId(1L);
        group.setUpdateUserId(1L);
        group.setCreateTime(new Date());
        group.setUpdateTime(new Date());
        group.setStatus(0);
        Integer groupId = devopsAutotestApiGroupMapper.insert(group);//入库 返回主键ID
        return BaseResult.success();
    }

    /**
     * @param groupId
     * @param loginUser
     * @return
     * @description 根据分组ID删除分组信息
     */
    @Override
    public BaseResult deleteApiGroupByGroupId(Integer groupId, Map<String, String> loginUser) {
        Integer i = devopsAutotestApiGroupMapper.updateGroupStatus(groupId, Long.parseLong(loginUser.get("userId")));
        if(i>0){
            return BaseResult.success();
        }
        return BaseResult.fail();
    }

    /**
     * @param userId 用户ID
     * @return
     * @description 查询分组信息
     */
    @Override
    public BaseResult selectGroupsByUserId(Long userId) {
        List<DevopsAutotestApiGroup> groups = devopsAutotestApiGroupMapper.selectByUserId(userId);
        return BaseResult.success(groups, (long) (groups.size()), 0L);
    }

    /**
     * @param group
     * @param loginUser 用户信息
     * @return
     * @description 修改接口分组信息
     */
    @Override
    public BaseResult updateApiGroup(DevopsAutotestApiGroup group, Map<String, String> loginUser) {
        if (Objects.isNull(group) || Objects.isNull(group.getId()) || StringUtils.isEmpty(group.getGroupName()) || StringUtils.isEmpty(group.getBasepath())) {
            return BaseResult.paramerr();//参数异常
        }
        DevopsAutotestApiGroup dbGroup = devopsAutotestApiGroupMapper.selectByPrimaryKey(group.getId());//查询接口分组信息
        if (Objects.isNull(dbGroup)) {
            //数据库不存在该分组 返回
            return BaseResult.fail();
        }
        dbGroup.setGroupName(group.getGroupName());
        dbGroup.setBasepath(group.getBasepath());
        dbGroup.setUpdateTime(new Date());
        dbGroup.setUpdateUserId(3L);
        devopsAutotestApiGroupMapper.updateByPrimaryKey(dbGroup);
        return null;
    }


    @Override
    public BaseResult selectApisByGoupId(DevopsAutotestApiGroup group, Map<String, String> loginUser) {
        return null;
    }
}
