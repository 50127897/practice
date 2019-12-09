package com.practice.pojo;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


public class Project_doc implements Serializable {
    @Id
    private Integer pdid;
    private String pdname;
    private Integer type;
    private Integer studentid;
    private String studentname;
    private Integer teacherid;
    private String teachername;
    private Date time;
    private String url;

    public Integer getPdid() {
        return pdid;
    }

    public void setPdid(Integer pdid) {
        this.pdid = pdid;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Project_doc{" +
                "pdid=" + pdid +
                ", pdname='" + pdname + '\'' +
                ", type=" + type +
                ", studentid=" + studentid +
                ", studentname='" + studentname + '\'' +
                ", teacherid=" + teacherid +
                ", teachername='" + teachername + '\'' +
                ", time=" + time +
                ", url='" + url + '\'' +
                '}';
    }
}
