package com.practice.dto;

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
    private Date time;

}
