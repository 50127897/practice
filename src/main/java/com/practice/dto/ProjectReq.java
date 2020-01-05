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

    /**
     * 项目id
     */
    @NotNull(message = "Project id can't be null")
    private Integer pId;

    /**
     * 项目状态
     */
    @Range(min = 3, max = 4, message = "Illegal status value")
    private Integer status;

    private Integer teacherId;

    private Integer current;


    private Integer size;

    private String pName;

    /**
     * 根据老师名称模糊查询
     */
    private String teacherNameLike;

    /**
     * 是否满人
     */
    private Integer isFull;

    private String rejectContent;

    private Integer adminId;

}
