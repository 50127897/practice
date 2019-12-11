package com.practice.dto;

import lombok.Data;

/**
 * 登陆响应信息
 */
@Data
public class LoginResp {
    /**
     * 用户id
     */
    private Integer mId;

    /**
     * 登陆类型
     */
    private Integer type;
}