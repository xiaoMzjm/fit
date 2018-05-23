package com.fit.org.api.model.query;

/**
 * @author:黑绝
 * @date:2018/5/19 下午10:01
 */
public class BaseQuery {

    private Integer pageIndex = 0;

    private Integer pageSize = 20;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
