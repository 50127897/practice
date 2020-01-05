package com.practice.dto;

import lombok.Data;


@Data
public class ResultResp {
    /**
     * 项目编号
     */
    private Integer pid;
    /**
     * 项目编号
     */
    private Integer teacherId;
    /**
     * 项目名称
     */
    private String pName;
    /**
     * 内容
     */
    private String content;

    /**
     * 文档
     */
    private String file;

    /**
     * 教师邮箱
     */
    private String email;

    /**
     *
     * 教师姓名
     */
    private String name;

    /**
     * 教师地址
     */
    private String address;

    /**
     * 教师电话
     */
    private String phone;

}
