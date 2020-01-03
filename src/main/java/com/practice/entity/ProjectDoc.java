package com.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
     */
    private Integer type;

    /**
     * 学生id
     */
    @TableField(value = "student_id")
    private Integer studentId;

    /**
     * 上传日期
     */
    @TableField(value = "create_time")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return pdId;
    }
}
