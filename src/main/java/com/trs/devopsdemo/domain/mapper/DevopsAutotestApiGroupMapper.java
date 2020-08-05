package com.trs.devopsdemo.domain.mapper;

import com.trs.devopsdemo.domain.model.DevopsAutotestApiGroup;

import java.util.List;

public interface DevopsAutotestApiGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DevopsAutotestApiGroup record);

    int insertSelective(DevopsAutotestApiGroup record);

    DevopsAutotestApiGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DevopsAutotestApiGroup record);

    int updateByPrimaryKey(DevopsAutotestApiGroup record);

    Integer updateGroupStatus(Integer groupId, long userId);

    List<DevopsAutotestApiGroup> selectByUserId(Long userId);
}