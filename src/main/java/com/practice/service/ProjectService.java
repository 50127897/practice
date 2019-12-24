package com.practice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.dto.BaseResp;
import com.practice.dto.ProjectStudent;
import com.practice.entity.Choice;
import com.practice.entity.Member;
import com.practice.mapper.ProjectMapper;
import com.practice.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
                : ResponseEntity.ok(BaseResp.fail("00001", "修改失败"));
    }

    public ResponseEntity<BaseResp> delete(Integer pid) {
        int result = projectMapper.deleteById(pid);
        return result == 1 ? ResponseEntity.ok(BaseResp.success())
                : ResponseEntity.ok(BaseResp.fail("00001", "删除失败"));
    }

    public ResponseEntity publish(Integer pId) {
        Project project = projectMapper.selectById(pId);
        project.setStatus(2);
        int result = projectMapper.updateById(project);
        return result == 1 ? ResponseEntity.ok(BaseResp.success())
                : ResponseEntity.ok(BaseResp.fail("00001", "发布失败"));
    }

    public ResponseEntity cancel(Integer pId) {
        Project project = projectMapper.selectById(pId);
        project.setStatus(1);
        int result = projectMapper.updateById(project);
        return result == 1 ? ResponseEntity.ok(BaseResp.success())
                : ResponseEntity.ok(BaseResp.fail("00001", "撤销失败"));
    }


    public ResponseEntity getStudentInfo(ProjectStudent projectStudent) {
        Project project = (Project) new Project().selectOne(new QueryWrapper<Project>().eq("p_id", projectStudent.getPId()));
        List<Member> members = new LinkedList<>();
        Integer type = projectStudent.getType();
        if (type == null) {
            JSON.parseObject(project.getSelectedIds(), new TypeReference<List<Integer>>(){}).forEach(
                    id -> members.add((Member) new Member().selectById((Integer) id))
            );
        } else {
            List<Choice> choices = new Choice().selectList(new QueryWrapper<Choice>().eq("p_id", projectStudent.getPId()).eq("type", type));
            choices.forEach(id -> members.add((Member) new Member().selectById(id)));
        }
        return ResponseEntity.ok(members);
    }



}
