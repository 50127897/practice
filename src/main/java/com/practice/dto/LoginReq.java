package com.practice.dto;

import lombok.Data;

/**
 * 登陆请求dto
 */
@Data
public class LoginReq {
    /**
     * 登陆用户类型
     */
    private Integer type;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
