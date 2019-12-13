package com.practice.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hap
 * 人员(学生，老师，管理员)
 */
@Data
public class Member extends Model implements Serializable {

    /**
     * 人员编号
     */
    private Integer mId;

    /**
     * 用户名（学号）
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 类型 1管理员 2教师 3学生
     */
    private Integer type;

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

    /**
     * 是否被选中
     */
    private Integer selected;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学生的老师id
     */
    private Integer teacherId;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

    @Override
    protected Serializable pkVal() {
        return mId;
    }
}
