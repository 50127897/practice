package com.practice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.util.Date;

/**
 * 通知数据
 */
@Data
public class AnnounceResp {
    /**
     * 通知id
     */
    private Integer aId;
    /**
     * 通知标题
     */
    private String title;
    /**
     * 通知时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
    /**
     *通知内容
     */
    private String content;
}
