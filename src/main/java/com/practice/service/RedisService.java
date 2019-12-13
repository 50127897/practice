package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author hzq
 * @date 2019/12/13
 */
@Service
public class RedisService {

    private RedisTemplate<String, Object> redisTemplate;
    private ValueOperations<String, Object> opsValue;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.opsValue = redisTemplate.opsForValue();
    }

    
    public void setValue(String key, Object value) {
        opsValue.set(key, value);
    }

    
    public void setValue(String key, Object value, long timeout, TimeUnit timeUnit) {
        opsValue.set(key, value, timeout, timeUnit);
    }

    
    public boolean setIfAbsent(String key, Object value, long timeout, TimeUnit timeUnit) {
        Boolean flag = opsValue.setIfAbsent(key, value, timeout, timeUnit);
        if(flag == null){
            return false;
        }else{
            return flag;
        }
    }

    
    public Object getValue(String key) {
        return opsValue.get(key);
    }

    
    public boolean delete(String key) {
        Boolean flag = redisTemplate.delete(key);
        if(flag == null){
            return false;
        }else{
            return flag;
        }
    }

    
    public boolean hasKey(String key) {
        Boolean flag = redisTemplate.hasKey(key);
        if(flag == null){
            return false;
        }else{
            return flag;
        }
    }

    
    public Object existKey(String key) {
        return hasKey(key) ? getValue(key) : null;
    }

    
    public Long decrement(String key) {
        return opsValue.decrement(key);
    }

}
