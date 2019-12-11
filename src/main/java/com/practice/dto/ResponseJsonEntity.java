package com.practice.dto;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author hzq
 * @date 2019/12/11
 */
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hzq
 * @date 2019/11/9
 */
public class ResponseJsonEntity<T> extends ResponseEntity<T> {

    public ResponseJsonEntity(HttpStatus status) {
        super(status);
    }

    private ResponseJsonEntity(T body, HttpStatus status) {
        super(body, status);
    }

    public static ResponseJsonEntity ok(String message) {
        return createEntity(HttpStatus.OK, message);
    }

    public static ResponseJsonEntity ok(Map map) {
        return new ResponseJsonEntity<>(JSON.toJSONString(map), HttpStatus.OK);
    }

    public static ResponseJsonEntity notFound(String message) {
        return createEntity(HttpStatus.NOT_FOUND, message);
    }

    public static ResponseJsonEntity badRequest(String message) {
        return createEntity(HttpStatus.BAD_REQUEST, message);
    }

    public static ResponseJsonEntity notAcceptable(String message) {
        return createEntity(HttpStatus.NOT_ACCEPTABLE, message);
    }

    private static ResponseJsonEntity createEntity(HttpStatus status, String message) {
        Map<String, String> map = new HashMap<>(1);
        map.put("message", message);
        return new ResponseJsonEntity<>(JSON.toJSONString(map), status);
    }

}

