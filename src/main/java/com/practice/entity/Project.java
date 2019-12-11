package com.practice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author hap
 *项目表
 */
@Data
@Table(name = "project",schema = "practice_system")
public class Project implements Serializable {

    /**
     * 项目编号
     */
    @Id
    @Column(name = "p_id",columnDefinition = "int")
    private Integer pId;

    /**
     * 教师id
     */
    @Column(name = "teacher_id",columnDefinition = "int")
    private Integer teacherId;

    /**
     * 项目名称
     */
    @Column(name = "p_name",columnDefinition = "varchar(20)")
    private String pName;

    /**
     * 需求人数
     */
    @Column(name = "member",columnDefinition = "tinyint")
    private Integer member;

    /**
     * 内容
     */
    @Column(name = "content",columnDefinition = "text")
    private String content;

    /**
     * 文档
     */
    @Column(name = "file",columnDefinition = "varchar(20)")
    private String file;

    /**
     * 教师名称
     */
    @Column(name = "teacher_name",columnDefinition = "varchar(12)")
    private String teacherName;

    /**
     * 是否满人
     */
    @Column(name = "is_full",columnDefinition = "tinyint")
    private Integer isFull;

    /**
     * 状态 1创建 2审核 3驳回 4通过
     */
    @Column(name = "status",columnDefinition = "tinyint")
    private Integer status;

    /**
     *  已选人数
     */
    @Column(name = "selected",columnDefinition = "tinyint")
    private Integer selected;

    /**
     * 第一志愿人数
     */
    @Column(name = "first",columnDefinition = "tinyint")
    private Integer first;

    /**
     * 第二志愿人数
     */
    @Column(name = "second",columnDefinition = "tinyint")
    private Integer second;

    /**
     * 第三志愿人数
     */
    @Column(name = "third",columnDefinition = "tinyint")
    private Integer third;


}
