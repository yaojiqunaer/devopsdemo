package com.trs.devopsdemo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

//一律通过建造器创建对象
@Builder
@Data
public class InterfaceSet {
    private Integer id;

    private String title;

    private String des;

    private Date createtime;

    private Date updatetime;

    private Integer status;

}