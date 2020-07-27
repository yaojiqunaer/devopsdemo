package com.trs.devopsdemo.domain.page;

import java.io.Serializable;
/**
 * @description 分页
 */
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;
    // 当前页
    private Integer pageNo = 1;
    // 页大小
    private Integer pageSize = 10;
    // 总记录 数
    private Integer totalRecord;
    // 总页数
    private Integer totalPage;
    // 关键字类型
    private String keyType;
    // 查询关键字
    private String keyWord;
    // 开始记录位置
    private Integer start;
    // 用户id
    private Long userId;
    // 其他用户id
    private String otherId;

    public Page() {
        super();
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalPage() {
        totalPage = (totalRecord - 1) / pageSize + 1;
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getStart() {
        start = (pageNo - 1) * pageSize;
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }


    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalRecord=" + totalRecord +
                ", totalPage=" + totalPage +
                ", keyType='" + keyType + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", start=" + start +
                ", userId=" + userId +
                ", otherId='" + otherId + '\'' +
                '}';
    }
}
