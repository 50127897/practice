package com.practice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author hap
 *项目学生表
 */
@Data
@Table(name = "pm",schema = "practice_system")
public class Pm implements Serializable {

    /**
     * 项目学生表编号
     */
    @Id
    @Column(name = "pm_id",columnDefinition = "int")
    private Integer pmId;

    /**
     * 项目id
     */
    @Column(name = "p_id",columnDefinition = "int")
    private Integer pId;

    /**
     * 学生id
     */
    @Column(name = "m_id",columnDefinition = "int")
    private Integer mId;


}
