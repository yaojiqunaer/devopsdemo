package com.trs.devopsdemo.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class DevopsAutotestApiGroup {
    private Long id;

    private String groupName;

    private String basepath;

    private Date createTime;

    private Date updateTime;

    private Long createUserId;

    private Long updateUserId;

    private Integer status;
    
}