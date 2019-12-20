package com.practice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hzq
 * @date 2019/12/20
 */
@Data
public class ProjectStudent {

    @NotNull(message = "Project id can't be null")
    private Integer pId;

    private Integer type;
}
