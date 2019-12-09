package com.practice.Entiiy;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hap
 * 通知
 */
@Data
@Table(name = "announce",schema = "practice_system")
public class Announce implements Serializable {

    /**
     *通知编号
     */
    @Id
    @Column(name = "a_id",columnDefinition = "int")
    private Integer aId;

    /**
     *通知类型 1教师 2学生
     */
    @Column(name = "type",columnDefinition = "tinyint")
    private Integer type;

    /**
     *通知标题
     */
    @Column(name = "title",columnDefinition = "varchar(32)")
    private String title;

    /**
     *通知内容
     */
    @Column(name = "content",columnDefinition = "text")
    private String content;

    /**
     *通知创建日期
     */
    @Column(name = "time",columnDefinition = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;


}
