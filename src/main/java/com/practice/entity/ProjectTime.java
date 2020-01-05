package com.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("project_time")
public class ProjectTime extends Model implements Serializable {

    /**
     * 时间编号
     */
    @TableId(value = "pt_id",type = IdType.AUTO)
    private Integer ptId;


    /**
     * 时间类型
     * 1老师发布项目时间
     * 2学生第一次选择时间
     * 3老师第一次选择时间
     * 4学生第二次选择时间
     * 5老师第二次选择时间
     * 6老师第三次选择时间
     * 7项目最终提交时间
     */
    private Integer type;


    /**
     * 开始日期
     */
    @TableField(value = "begin_time")
    private Date beginTime;

    /**
     * 结束日期
     */
    @TableField(value = "end_time")
    private Date endTime;

    @Override
    protected Serializable pkVal() {
        return ptId;
    }
}
