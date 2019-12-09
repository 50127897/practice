package com.practice.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author huanganping
 */
@Data
public class ProjectDoc implements Serializable {

    @Id
    private Integer pdId;

    private String pdName;

    private Integer type;

    private Integer studentId;

    private String studentName;

    private Integer teacherId;

    private String teacherName;

    private Date time;

    private String url;
}
