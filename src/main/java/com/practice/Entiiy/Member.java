package com.practice.Entiiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author hap
 * 人员(学生，老师，管理员)
 */
@Data
@Table(name = "member",schema = "practice_system")
public class Member implements Serializable {

    /**
     * 人员编号
     */
    @Id
    @Column(name = "m_id",columnDefinition = "int")
    private Integer mId;

    /**
     * 用户名（学号）
     */
    @Column(name = "user_name",columnDefinition = "char(11)")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password",columnDefinition = "varchar(32)")
    private String password;

    /**
     * 类型 1管理员 2教师 3学生
     */
    @Column(name = "type",columnDefinition = "tinyint")
    private Integer type;

    /**
     * 学院
     */
    @Column(name = "college",columnDefinition = "varchar(12)")
    private String college;

    /**
     * 专业
     */
    @Column(name = "major",columnDefinition = "varchar(12)")
    private String major;

    /**
     * 年级
     */
    @Column(name = "grade",columnDefinition = "tinyint")
    private Integer grade;

    /**
     * 班级
     */
    @Column(name = "classes",columnDefinition = "tinyint")
    private Integer classes;

    /**
     * 地址
     */
    @Column(name = "address",columnDefinition = "varchar(20)")
    private String address;

    /**
     * 电话
     */
    @Column(name = "phone",columnDefinition = "char(12)")
    private String phone;

    /**
     *
     * 姓名
     */
    @Column(name = "name",columnDefinition = "varchar(12)")
    private String name;

    /**
     * 是否被选中
     */
    @Column(name = "selected",columnDefinition = "tinyint")
    private Integer selected;

    /**
     * 邮箱
     */
    @Column(name = "email",columnDefinition = "varchar(20)")
    private String email;

    /**
     * 学生的老师id
     */
    @Column(name = "teacher_id",columnDefinition = "int")
    private Integer teacherId;

    /**
     * 项目id
     */
    @Column(name = "project_id",columnDefinition = "int")
    private Integer projectId;

    /**
     * 项目名称
     */
    @Column(name = "project_name",columnDefinition = "varchar(20)")
    private String projectName;


}
