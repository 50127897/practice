package com.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.data.annotation.Transient;

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
    @TableId(value = "m_id",type = IdType.AUTO)
    private Integer mId;

    /**
     * 用户名（学号）
     */
    @TableField(value = "user_name")
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
    @TableField(value = "teacher_id")
    private Integer teacherId;

    /**
     * 项目id
     */
    @TableField(value = "project_id")
    private Integer projectId;

    /**
     * 项目名称
     */
    @TableField(value = "project_name")
    private String projectName;


    /**
     * 前端选中标志
     */
    @TableField(exist=false)
    private Integer choose = 0;

    @Override
    protected Serializable pkVal() {
        return mId;
    }
}
