package com.practice.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hap
 *志愿
 */
@Data
public class Choice extends Model implements Serializable {

    /**
     * 志愿编号
     */
    private Integer cId;

    /**
     * 项目id
     */
    @NotNull(message = "Project id can't be null")
    private Integer pId;

    /**
     * 学生id
     */
    @NotNull(message = "Student id can't be null")
    private Integer mId;

    /**
     * 类型 1第一志愿 2第二志愿 3第三志愿
     */
    @NotNull(message = "Choice type can't be null")
    private Integer type;

    @Override
    protected Serializable pkVal() {
        return cId;
    }
}
