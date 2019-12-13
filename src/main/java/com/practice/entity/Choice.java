package com.practice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hap
 *志愿
 */
@Data
public class Choice implements Serializable {

    /**
     * 志愿编号
     */
    private Integer cId;

    /**
     * 项目id
     */
    private Integer pId;

    /**
     * 学生id
     */
    private Integer mId;

    /**
     * 类型 1第一志愿 2第二志愿 3第三志愿
     */
    private Integer type;


}
