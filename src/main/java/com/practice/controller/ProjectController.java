package com.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.practice.annotation.Access;
import com.practice.dto.*;
import com.practice.entity.Choice;
import com.practice.entity.Project;
import com.practice.service.MessageService;
import com.practice.status.AccessPeople;
import com.practice.status.ProjectStatus;
import com.practice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author user
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 添加项目
     * @param project
     * @return
     */
    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<BaseResp> addProject(@RequestBody @Validated Project project) {
        return projectService.addProject(project);
    }

    /**
     * 更改项目
     * @param project
     * @return
     */
    @PutMapping
    public ResponseEntity<BaseResp> change(@RequestBody @Validated Project project) {
        return projectService.change(project);
    }


    /**
     * 老师删除未发布课程
     * @param pId 课程id
     * @return
     */
    @DeleteMapping("/{pId}")
    public ResponseEntity delete(@PathVariable Integer pId){
        return projectService.delete(pId);
    }

    /**
     * 老师申请课程审核
     *
     * @param pId 课程id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntity> publish(@PathVariable("id") Integer pId) {
        return ResponseEntity.ok(projectService.publish(pId));
    }

    /**
     * 老师撤销申请课程审核
     * @param pId 课程id
     * @return
     */
    @PutMapping("/cancel/{id}")
    public ResponseEntity<ResponseEntity> cancel(@PathVariable("id") Integer pId){
        return ResponseEntity.ok(projectService.cancel(pId));
    }



    /**
     * 根据传入不为空的参数筛选课程
     * 老师ID
     * 状态
     * 课程列表
     */
    @GetMapping
    public ResponseEntity getProject(@RequestBody ProjectReq req) {
        boolean idFlag = req.getPId() != null;
        boolean statusFlag = req.getStatus() != null && ProjectStatus.isIllegal(req.getStatus());
        boolean teacherIdFlag = req.getTeacherId() != null;
        boolean pNameFlag = req.getPName() != null;
        boolean teacherNameFlag = req.getTeacherNameLike() != null;
        boolean isFullFlag = req.getIsFull() != null;

        req.setCurrent(req.getCurrent()==null?1:req.getCurrent());
        req.setSize(req.getSize()==null?14:req.getSize());
        IPage projectList = new Project().selectPage(new Page<Project>(req.getCurrent(),req.getSize()),
                new QueryWrapper<Project>().eq(statusFlag,"status",req.getStatus())
                                           .eq(idFlag,"p_id",req.getPId())
                                           .eq(teacherIdFlag, "teacher_id",req.getTeacherId())
                                            .like(pNameFlag,"p_name",req.getPName())
                                            .eq(isFullFlag,"is_full",req.getIsFull())
                                            .like(teacherNameFlag,"teacher_name",req.getTeacherNameLike())
        );
        return ResponseEntity.ok(projectList);
    }

    /**
     * 管理员审批待申请课程
     */
    @Access(AccessPeople.Admin)
    @PatchMapping
    public ResponseEntity approve(@RequestBody @Validated ProjectReq req){
        Project project = (Project) new Project().selectById(req.getPId());
        if (project == null) {
            return ResponseJsonEntity.badRequest("Illegal project id");
        }
        int status = req.getStatus();
        project.setStatus(status);
        if (status == ProjectStatus.Reject.getStatus()) {
            project.setRejectContent(req.getRejectContent());
            messageService.sendToSomeBody(project.getTeacherId(), "《" + project.getPName() + "》审核失败", req.getRejectContent(), req.getAdminId());
        } else {
            messageService.sendToSomeBody(project.getTeacherId(), "《" + project.getPName() + "》审核成功", null, req.getAdminId());
        }
        project.insertOrUpdate();
        return ResponseJsonEntity.ok("Approve success");
    }


    /**
     * 教师查看学生选择志愿信息
     * @param student
     * @return
     */
    @Access(exclude = AccessPeople.Student)
    @GetMapping("/stuChoice")
    public ResponseEntity getStuChoiceOnProject(ProjectStudent student){
        return projectService.getStuChoiceOnProject(student);
    }


    /**
     * 获取项目中已录取的学生信息
     * @param student
     * @return
     */
    @Access(exclude = AccessPeople.Student)
    @GetMapping("/students")
    public ResponseEntity getStuOnProject(@RequestBody ProjectStudent student) {
        return projectService.getStudentInfo(student);
    }


    /**
     * 学生提交志愿
     * @param choices
     * @return
     */
    @PostMapping("/choice")
    public ResponseEntity choiceProject(@RequestBody List<Choice> choices) {
        choices.forEach(Model::insertOrUpdate);
        return ResponseJsonEntity.ok("选择成功");
    }

    /**
     * 获取学生志愿信息
     * @param mid
     * @return
     */
    @GetMapping("/choice")
    public ResponseEntity getChoice(Integer mid){
        List list = new Choice().selectList(new QueryWrapper<Choice>().eq("m_id", mid));
        return ObjectUtils.isEmpty(list)?ResponseEntity.ok(Collections.EMPTY_LIST):ResponseEntity.ok(list);
    }


    /**
     * 老师选择学生
     * @param list
     * @return
     */
    @PostMapping("/submitChoice")
    public ResponseEntity chooseStudent(@RequestBody List<Choice> list){
        Project project = (Project) new Project().selectById(list.get(0).getPId());
        if (project.getMember() < project.getSelected()+list.size()){
            return ResponseEntity.ok("所选人数超出所需人数");
        }
        return this.projectService.chooseStudent(list);
    }

    /**
     * 学生获取志愿结果
     * @param mid
     * @return
     */
    @GetMapping("/result")
    public ResponseEntity<ResultResp> getResult(Integer mid){
        return projectService.getResult(mid);
    }

}
