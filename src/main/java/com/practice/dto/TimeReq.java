package com.practice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TimeReq {
    //发布开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date publishStart;
    //学生第一次选择开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date stuFirstChoiceStart;
    //教师第一次选择开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date teacherFirstChoiceStart;
    //学生第二次选择开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date stuSecondChoiceStart;
    //教师第二次选择开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date teacherSecondChoiceStart;
    //教师第三次选择开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date teacherThirdChoiceStart;
    //项目结束开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date projectFinalStart;
    //发布结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date publishEnd;
    //学生第一次选择结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date stuFirstChoiceEnd;
    //教师第一次选择结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date teacherFirstChoiceEnd;
    //学生第二次选择结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date stuSecondChoiceEnd;
    //教师第二次选择结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date teacherSecondChoiceEnd;
    //教师第三次选择结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date teacherThirdChoiceEnd;
    //项目结束结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date projectFinalEnd;
}
