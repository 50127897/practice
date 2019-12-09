package com.practice.service;

import com.practice.mapper.AnnounceMapper;
import com.practice.Entiiy.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class AnnounceService {

    @Autowired
    private AnnounceMapper announceMapper;

    public List<Announce> selectAnnounce(Integer type, String title) {
        Example example = new Example(Announce.class);
        Example.Criteria criteria = example.createCriteria();
        if(type!=null){
            criteria.andEqualTo("type",type);
        }
        if(!StringUtils.isEmpty(title)){
            criteria.andLike("title","%"+title+"%").orEqualTo("title",title);

        }
        return announceMapper.selectByExample(example);
    }

    public int deleteById(Integer id) {
        return this.announceMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public int addAnnounce(Announce announce) {
        announce.setTime(new Date());
        return this.announceMapper.insertSelective(announce);
    }
}
