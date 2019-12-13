package com.practice.service;

import com.practice.dto.BaseResp;
import com.practice.mapper.ProjectMapper;
import com.practice.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public ResponseEntity<BaseResp> addProject(Project project) {
        int result = this.projectMapper.insert(project);
        return result == 1 ? ResponseEntity.ok(BaseResp.success())
                : ResponseEntity.ok(BaseResp.fail());
    }


    public ResponseEntity<BaseResp> change(Project project) {
        int result = projectMapper.updateById(project);
        return result == 1 ? ResponseEntity.ok(BaseResp.success())
                : ResponseEntity.ok(BaseResp.fail("00001","修改失败"));
    }

    public ResponseEntity publish(Integer pId) {
        Project project = projectMapper.selectById(pId);
        project.setStatus(2);
        int result = projectMapper.updateById(project);
        return result == 1 ? ResponseEntity.ok(BaseResp.success())
                : ResponseEntity.ok(BaseResp.fail("00001","发布失败"));
    }


}
