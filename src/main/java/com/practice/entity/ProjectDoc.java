package com.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hap
 * 项目文档表
 */
@Data
public class ProjectDoc extends Model implements Serializable {

    public ProjectDoc(){}

    public ProjectDoc(int studentId, int type, String fileName){
        this.studentId = studentId;
        this.type = type;
        this.pdName = fileName;
        this.createTime = new Date();
    }

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
     * 1. 项目分工说明书
     * 2. 项目规划说明书
     * 3. 需求分析说明书
     * 4. 概要设计说明书
     * 5. 详细设计说明书
     * 6. 系统测试报告
     * 7. 推广实施说明书
     * 8. 资金预算表
     * 9. 资金执行计划表
     * 10. 用户手册
     * 11. 工作周志
     * 12. 项目工程实践报告
     * 13. 项目工程实践成果登记表
     * 14. 讲座听后感
     * 15.项目详细说明书
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
     * 教师编号
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;

    /**
     * 项目编号
     */
    @TableField(value = "pid")
    private Integer pId;

    /**
     * 上传日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    @TableField(value = "create_time")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return pdId;
    }
}
