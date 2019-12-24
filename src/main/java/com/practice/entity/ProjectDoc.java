package com.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "pd_id",type = IdType.AUTO)
    private Integer pdId;

    /**
     * 文档名称
     */
    @TableField(value = "pd_name")
    private String pdName;

    /**
     * 文档类型
     */
    private Integer type;

    /**
     * 学生id
     */
    @TableField(value = "student_id")
    private Integer studentId;

    /**
     * 学生姓名
     */
    @TableField(value = "student_name")
    private String studentName;

    /**
     * 教师id
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;

    /**
     * 教师名称
     */
    @TableField(value = "teacher_name")
    private String teacherName;

    /**
     * 上传日期
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 文档路径
     */
    private String url;
}
