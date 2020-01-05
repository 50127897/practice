package com.practice.entity;

import lombok.Data;

import javax.persistence.*;

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
}
