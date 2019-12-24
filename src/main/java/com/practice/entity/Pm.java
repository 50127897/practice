package com.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hap
 *项目学生表
 */
@Data
public class Pm implements Serializable {

    /**
     * 项目学生表编号
     */
    @TableId(value = "pm_id",type = IdType.AUTO)
    private Integer pmId;

    /**
     * 项目id
     */
    @TableField(value = "p_id")
    private Integer pId;

    /**
     * 学生id
     */
    @TableField(value = "m_id")
    private Integer mId;


}
