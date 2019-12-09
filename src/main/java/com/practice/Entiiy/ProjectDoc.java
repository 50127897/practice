package com.practice.Entiiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hap
 * 项目文档表
 */
@Data
@Table(name = "project_doc",schema = "practice_system")
public class ProjectDoc implements Serializable {

    /**
     * 文档编号
     */
    @Id
    @Column(name = "pd_id",columnDefinition = "int")
    private Integer pdId;

    /**
     * 文档名称
     */
    @Column(name = "pd_name",columnDefinition = "int")
    private String pdName;

    /**
     * 文档类型
     */
    @Column(name = "type",columnDefinition = "int")
    private Integer type;

    /**
     * 学生id
     */
    @Column(name = "student_id",columnDefinition = "int")
    private Integer studentId;

    /**
     * 学生姓名
     */
    @Column(name = "student_name",columnDefinition = "int")
    private String studentName;

    /**
     * 教师id
     */
    @Column(name = "teacher_id",columnDefinition = "int")
    private Integer teacherId;

    /**
     * 教师名称
     */
    @Column(name = "teacher_name",columnDefinition = "int")
    private String teacherName;

    /**
     * 上传日期
     */
    @Column(name = "time",columnDefinition = "int")
    private Date time;

    /**
     * 文档路径
     */
    @Column(name = "url",columnDefinition = "int")
    private String url;
}
