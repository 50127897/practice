package com.practice.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;


public class Choice implements Serializable {
    @Id
    private Integer cid;
    private Integer pid;
    private Integer mid;
    private Integer type;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "cid=" + cid +
                ", pid=" + pid +
                ", mid=" + mid +
                ", type=" + type +
                '}';
    }
}
