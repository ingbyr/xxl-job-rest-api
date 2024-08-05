package com.xxl.job.admin.service;

public class XxlJobPageDataDTO<T> {
    private Integer total;
    private T data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
