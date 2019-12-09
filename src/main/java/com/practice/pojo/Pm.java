package com.practice.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

public class Pm implements Serializable {
    @Id
    private Integer pmid;
    private Integer pid;
    private Integer mid;

    public Integer getPmid() {
        return pmid;
    }

    public void setPmid(Integer pmid) {
        this.pmid = pmid;
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

    @Override
    public String toString() {
        return "Pm{" +
                "pmid=" + pmid +
                ", pid=" + pid +
                ", mid=" + mid +
                '}';
    }
}
