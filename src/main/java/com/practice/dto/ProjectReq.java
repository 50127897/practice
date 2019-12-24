package com.practice.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hzq
 * @date 2019/12/13
 */
@Data
public class ProjectReq implements Serializable {

    @NotNull(message = "Project id can't be null")
    private Integer pId;

    @Range(min = 3, max = 4, message = "Illegal status value")
    private Integer status;

    private Integer teacherId;

    private Integer current = 1;


    private Integer size = 14;

    private String pName;



    private String rejectContent;
}
