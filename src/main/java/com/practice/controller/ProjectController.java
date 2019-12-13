package com.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.dto.BaseResp;
import com.practice.dto.ProjectReq;
import com.practice.dto.ResponseJsonEntity;
import com.practice.entity.Project;
import com.practice.status.ProjectStatus;
import com.practice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<BaseResp> addProject(@RequestBody @Validated Project project){
        return projectService.addProject(project);
    }

    @PutMapping
    public ResponseEntity<BaseResp> change(@RequestBody @Validated Project project){
        return projectService.change(project);
    }


    /**
     * 老师申请课程审核
     * @param pId 课程id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntity> publish(@PathVariable("id") Integer pId){
        return ResponseEntity.ok(projectService.publish(pId));
    }

    /**
     * 根据传入不为空的参数筛选课程
     * 老师ID
     * 状态
     * 课程列表
     */
    @GetMapping
    public ResponseEntity getProject(@RequestBody ProjectReq req){
        boolean idFlag = req.getPId() != null;
        boolean statusFlag = req.getStatus() != null && ProjectStatus.isIllegal(req.getStatus());
        boolean teacherIdFlag = req.getTeacherId() != null;
        List projectList = new Project().selectList(
                new QueryWrapper<Project>().eq(statusFlag,"state",req.getStatus())
                                           .eq(idFlag,"p_id",req.getPId())
                                           .eq(teacherIdFlag, "teacher_id",req.getTeacherId()));
        return ResponseEntity.ok(projectList);
    }

    /**
     * 管理员审批待申请课程
     */
    @PatchMapping
    public ResponseEntity approve(@RequestBody @Validated ProjectReq req){
        Project project = (Project) new Project().selectById(req.getPId());
        if(project == null){
            return ResponseJsonEntity.badRequest("Illegal project id");
        }
        int status = req.getStatus();
        project.setStatus(status);
        if(status == ProjectStatus.Reject.getStatus()){
            project.setRejectContent(req.getRejectContent());
        }
        project.insertOrUpdate();
        return ResponseJsonEntity.ok("Approve success");
    }

}
