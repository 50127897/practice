package com.practice.entity;

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
    private Integer pmId;

    /**
     * 项目id
     */
    private Integer pId;

    /**
     * 学生id
     */
    private Integer mId;


}
