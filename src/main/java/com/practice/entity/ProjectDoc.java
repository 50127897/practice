package com.practice.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hap
 * 项目文档表
 */
@Data
public class ProjectDoc implements Serializable {

    /**
     * 文档编号
     */
    private Integer pdId;

    /**
     * 文档名称
     */
    private String pdName;

    /**
     * 文档类型
     */
    private Integer type;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 上传日期
     */
    private Date createTime;

    /**
     * 文档路径
     */
    private String url;
}
