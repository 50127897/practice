package com.practice.service;

import com.practice.mapper.MemberMapper;
import com.practice.Entiiy.Member;
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
            criteria.andEqualTo("username", member.getUserName());
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
}
