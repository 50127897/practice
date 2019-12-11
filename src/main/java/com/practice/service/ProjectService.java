package com.practice.service;

import com.practice.dto.BaseResp;
import com.practice.mapper.ProjectMapper;
import com.practice.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public ResponseEntity<BaseResp> addProject(Project project) {
        int result = this.projectMapper.insertSelective(project);
        return result == 1 ? ResponseEntity.ok(BaseResp.success())
                : ResponseEntity.ok(BaseResp.fail());
    }

    public List<Project> queryAllMyProject(Integer teacherId) {
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teacher_id", teacherId);
        return this.projectMapper.selectByExample(example);
    }

    public ResponseEntity<BaseResp> change(Project project) {
        int result = projectMapper.updateByPrimaryKey(project);
        return result == 1 ? ResponseEntity.ok(BaseResp.success())
                : ResponseEntity.ok(BaseResp.fail("00001","修改失败"));
    }

    public ResponseEntity publish(Integer pId) {
        Project project = projectMapper.selectByPrimaryKey(pId);
        project.setStatus(2);
        int result = projectMapper.updateByPrimaryKey(project);
        return result == 1 ? ResponseEntity.ok(BaseResp.success())
                : ResponseEntity.ok(BaseResp.fail("00001","发布失败"));
    }


}
