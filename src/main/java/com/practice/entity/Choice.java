package com.practice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author hap
 *志愿
 */
@Data
@Table(name = "choice",schema = "practice_system")
public class Choice implements Serializable {

    /**
     * 志愿编号
     */
    @Column(name = "c_id",columnDefinition = "int")
    @Id
    private Integer cId;

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

    /**
     * 类型 1第一志愿 2第二志愿 3第三志愿
     */
    @Column(name = "type",columnDefinition = "tinyint")
    private Integer type;


}
