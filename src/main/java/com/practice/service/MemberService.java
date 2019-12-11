package com.practice.service;

import com.practice.mapper.MemberMapper;
import com.practice.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class MemberService {

    @Autowired
    private MemberMapper membermapper;

    public Member login(Member member) {
        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        if(member.getUserName()!=null) {
            criteria.andEqualTo("userName", member.getUserName());
        }
        if(member.getPassword()!=null) {
            criteria.andEqualTo("password", member.getPassword());
        }
        if(member.getType()!=null) {
            criteria.andEqualTo("type",member.getType());
        }

        return this.membermapper.selectOneByExample(example);
    }

    public Member getInfo(Integer mid) {
        return this.membermapper.selectByPrimaryKey(mid);
    }

    public Integer updateByPrimaryKey(Member member) {
        return this.membermapper.updateByPrimaryKeySelective(member);
    }

    public Member selectByUsername(String username) {
        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        if(username!=null) {
            criteria.andEqualTo("userName", username);
        }
        return this.membermapper.selectOneByExample(example);
    }
}
