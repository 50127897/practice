package com.practice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hzq
 * @date 2019/12/20
 */
@Data
@Entity
@Table(name = "t_message", schema = "practice_system")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(10)")
    private Integer id;

    @Column(name = "to_member", columnDefinition = "INT(10)")
    private Integer toMember;

    @Column(name = "from_member", columnDefinition = "INT(10)")
    private Integer fromMember;

    @Column(name = "title", columnDefinition = "VARCHAR(255)")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    @Column(name = "create_time", columnDefinition = "date")
    private Date createTime;
}
