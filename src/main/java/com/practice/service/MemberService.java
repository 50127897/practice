package com.practice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.mapper.MemberMapper;
import com.practice.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisService redisService;

    public Member getInfo(Integer mid) {
        return this.memberMapper.selectById(mid);
    }

    public Integer updateByPrimaryKey(Member member) {
        return this.memberMapper.updateById(member);
    }

    public Member selectByUsername(String userName) {
        return this.memberMapper.selectOne(new QueryWrapper<Member>().eq("user_name",userName));
    }

    public String saveToken(Member member) {
        String token = initToken(member.getUserName());
        redisService.setValue(token,member);
        return token;
    }

    private String initToken(String userName) {
        String str = System.currentTimeMillis() + userName + System.currentTimeMillis();
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public void logout(String token) {
        if(redisService.hasKey(token)){
            redisService.delete(token);
        }
    }
}
