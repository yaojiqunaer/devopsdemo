package com.trs.devopsdemo.domain.usecase;

import com.trs.devopsdemo.domain.api.ApiDTO;
import lombok.Data;

import java.util.List;

/**
 * @Title UsecaseDTO
 * @Description: 测试用例DTO
 * @Create Date: 2020/7/31 16:18
 * @Author Zhenjin.Zhang
 * @Contact: zhang.zhenjin@trs.com.cn
 * @Company: 成都拓尔思信息技术有限公司
 * @Department: 中台（Middle-End）
 */
@Data
public class UsecaseDTO {


    private Long usecaseSetId;
    private String usecaseName;
    private List<ApiDTO> requests;

}
