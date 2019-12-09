package com.practice.pojo;

import lombok.Data;

import java.io.Serializable;

public class Project implements Serializable {
    private Integer pid;
    private Integer teacherid;
    private String pname;
    private Integer member;
    private String content;
    private String file;
    private String teachername;
    private Integer isfull;
    private Integer status;
    private Integer selected;
    private Integer first;
    private Integer second;
    private Integer third;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public Integer getIsfull() {
        return isfull;
    }

    public void setIsfull(Integer isfull) {
        this.isfull = isfull;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getThird() {
        return third;
    }

    public void setThird(Integer third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "Project{" +
                "pid=" + pid +
                ", teacherid=" + teacherid +
                ", pname='" + pname + '\'' +
                ", member=" + member +
                ", content='" + content + '\'' +
                ", file='" + file + '\'' +
                ", teachername='" + teachername + '\'' +
                ", isfull=" + isfull +
                ", status=" + status +
                ", selected=" + selected +
                ", first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
