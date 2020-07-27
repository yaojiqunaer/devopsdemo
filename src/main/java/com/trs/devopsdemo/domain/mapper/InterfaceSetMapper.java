package com.trs.devopsdemo.domain.mapper;

import com.trs.devopsdemo.domain.model.InterfaceSet;

public interface InterfaceSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InterfaceSet record);

    int insertSelective(InterfaceSet record);

    InterfaceSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InterfaceSet record);

    int updateByPrimaryKey(InterfaceSet record);
}