package com.trs.devopsdemo.domain.mapper;

import com.trs.devopsdemo.domain.model.Interface;

public interface InterfaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Interface record);

    int insertSelective(Interface record);

    Interface selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Interface record);

    int updateByPrimaryKey(Interface record);
}