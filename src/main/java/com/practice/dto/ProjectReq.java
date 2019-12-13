package com.practice.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author hzq
 * @date 2019/12/13
 */
@Data
public class ProjectReq {

    @NotNull(message = "Project id can't be null")
    private Integer pId;

    @Range(min = 3, max = 4, message = "Illegal status value")
    private Integer status;

    private Integer teacherId;

    private String rejectContent;
}
