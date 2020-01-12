package com.fh.model;

import java.util.Date;

public class Category {

    private Integer id;

    private String name;

    private Integer pid;

    private Date createDate;

    private Date getCreateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getGetCreateDate() {
        return getCreateDate;
    }

    public void setGetCreateDate(Date getCreateDate) {
        this.getCreateDate = getCreateDate;
    }
}
