package com.practice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hap
 *项目表
 */
@Data
public class Project extends Model implements Serializable {

    /**
     * 项目编号
     */
    private Integer pId;

    /**
     * 教师id
     */
    @NotNull(message = "Teacher's id can't be null")
    private Integer teacherId;

    /**
     * 项目名称
     */
    @NotBlank(message = "Project's name can't be blank")
    private String pName;

    /**
     * 需求人数
     */
    @Min(value = 1, message = "Member's number can't less than 1")
    @NotNull(message = "member can't be null")
    private Integer member;

    /**
     * 内容
     */
    @NotBlank(message = "Content can't be blank")
    private String content;

    /**
     * 文档
     */
    private String file;

    /**
     * 教师名称
     */
    @NotBlank(message = "Teacher's name can't be blank")
    private String teacherName;

    /**
     * 是否满人
     */
    private Integer isFull;

    /**
     * 状态 1创建 2审核 3驳回 4通过
     */
    private Integer status;

    /**
     *  已选人数
     */
    private Integer selected;

    /**
     * 第一志愿人数
     */
    private Integer first;

    /**
     * 第二志愿人数
     */
    private Integer second;

    /**
     * 第三志愿人数
     */
    private Integer third;

    /**
     * 拒绝内容
     */
    private String rejectContent;

    /**
     * 选择的学生ID
     */
    @TableField("selected_students_id")
    private String selectedIds;

    @Override
    protected Serializable pkVal() {
        return pId;
    }
}
