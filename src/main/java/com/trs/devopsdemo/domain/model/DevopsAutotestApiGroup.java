package com.trs.devopsdemo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
public class DevopsAutotestApiGroup implements Serializable {

    private Long id;
    private String groupName;
    private String basepath;
    private Date createTime;
    private Date updateTime;
    private Long createUserId;
    private Long updateUserId;
    private Integer status;//0为存在 -1为删除

}