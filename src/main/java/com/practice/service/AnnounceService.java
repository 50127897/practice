package com.practice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.mapper.AnnounceMapper;
import com.practice.entity.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class AnnounceService {

    @Autowired
    private AnnounceMapper announceMapper;

    public List<Announce> selectAnnounce(Integer type, String title) {
        return announceMapper.selectList(
                new QueryWrapper<Announce>()
                        .eq(type!=null,"type",type)
                        .like(!StringUtils.isEmpty(title),"title",title));
    }


    public Announce selectByPrimaryKey(Integer aid){
        return this.announceMapper.selectById(aid);
    }
    public int deleteById(Integer id) {
        return this.announceMapper.deleteById(id);
    }

    public int addAnnounce(Announce announce) {
        announce.setCreateTime(new Date());
        return this.announceMapper.insert(announce);
    }
}
