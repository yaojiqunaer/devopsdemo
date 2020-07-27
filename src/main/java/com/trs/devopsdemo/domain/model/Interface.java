package com.trs.devopsdemo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Builder
@Data
public class Interface {
    private Integer id;

    private String title;

    private String path;

    private String method;

    private Integer listid;

    private Integer projectid;

    private Date createtime;

    private Date updatetime;

    private Integer userid;

    private Integer status;

}