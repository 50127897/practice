package com.practice.dto;

import lombok.Data;

@Data
public class StuReq {
    /**
     * 是否被选中
     */
    private Integer selected;
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
     * 当前页码
     */
    private Integer current;
    /**
     * 每页显示条数
     */
    private Integer size;
    /**
     *
     * 姓名
     */
    private String name;
    /**
     * 类型 1管理员 2教师 3学生
     */
    private Integer type = 3;
}
