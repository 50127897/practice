package com.practice.dto;

import lombok.Data;


/**
 * 用户响应dto
 */
@Data
public class MemberResp {
    /**
     * 用户名（学号）
     */
    private String userName;

    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 班级
     */
    private Integer classes;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     *
     * 姓名
     */
    private String name;
}
