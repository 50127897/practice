package com.practice.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hap
 * 通知
 */
@Data
public class Announce extends Model implements Serializable {

    /**
     *通知编号
     */

    private Integer aId;

    /**
     *通知类型 1教师 2学生
     */
    private Integer type;

    /**
     *通知标题
     */
    private String title;

    /**
     *通知内容
     */
    private String content;

    /**
     *通知创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return aId;
    }
}
