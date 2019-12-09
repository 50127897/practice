package com.practice.service;

import com.practice.mapper.ProjectMapper;
import com.practice.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public Integer addProject(Project project) {
        return this.projectMapper.insertSelective(project);
    }

    public List<Project> QueryAllMyProject(Integer teacherid) {
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teacherid",teacherid);
        return this.projectMapper.selectByExample(example);
    }
}
