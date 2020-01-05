package com.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.dto.TimeReq;
import com.practice.entity.ProjectTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/time")
public class TimeController {

    @PostMapping
    public ResponseEntity insertOrUpdateTime(@RequestBody TimeReq req){
        ProjectTime projectTime = new ProjectTime();
        List<ProjectTime> list = new ArrayList<>();
        //1老师发布项目时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 1));
        if (list.size() == 0){
            projectTime.setType(1);
            projectTime.setBeginTime(req.getPublishStart());
            projectTime.setEndTime(req.getPublishEnd());
            projectTime.insertOrUpdate();
        }else{
            projectTime = list.get(0);
            projectTime.setBeginTime(req.getPublishStart());
            projectTime.setEndTime(req.getPublishEnd());
            System.out.println();
            projectTime.updateById();
        }

        //2学生第一次选择时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 2));
        if (list.size() == 0){
            projectTime.setType(2);
            projectTime.setPtId(null);
            projectTime.setBeginTime(req.getStuFirstChoiceStart());
            projectTime.setEndTime(req.getStuFirstChoiceEnd());
            projectTime.insertOrUpdate();
        }else{
            projectTime = list.get(0);
            projectTime.setBeginTime(req.getStuFirstChoiceStart());
            projectTime.setEndTime(req.getStuFirstChoiceEnd());
            System.out.println();
            projectTime.updateById();
        }

        //3老师第一次选择时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 3));
        if (list.size() == 0){
            projectTime.setType(3);
            projectTime.setPtId(null);
            projectTime.setBeginTime(req.getTeacherFirstChoiceStart());
            projectTime.setEndTime(req.getTeacherFirstChoiceEnd());
            projectTime.insertOrUpdate();
        }else{
            projectTime = list.get(0);
            projectTime.setBeginTime(req.getTeacherFirstChoiceStart());
            projectTime.setEndTime(req.getTeacherFirstChoiceEnd());
            System.out.println();
            projectTime.updateById();
        }

        //4学生第二次选择时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 4));
        if (list.size() == 0){
            projectTime.setType(4);
            projectTime.setPtId(null);
            projectTime.setBeginTime(req.getStuSecondChoiceStart());
            projectTime.setEndTime(req.getStuSecondChoiceEnd());
            projectTime.insertOrUpdate();
        }else{
            projectTime = list.get(0);
            projectTime.setBeginTime(req.getStuSecondChoiceStart());
            projectTime.setEndTime(req.getStuSecondChoiceEnd());
            System.out.println();
            projectTime.updateById();
        }

        //5老师第二次选择时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 5));
        if (list.size() == 0){
            projectTime.setType(5);
            projectTime.setPtId(null);
            projectTime.setBeginTime(req.getTeacherSecondChoiceStart());
            projectTime.setEndTime(req.getTeacherSecondChoiceEnd());
            projectTime.insertOrUpdate();
        }else{
            projectTime = list.get(0);
            projectTime.setBeginTime(req.getTeacherSecondChoiceStart());
            projectTime.setEndTime(req.getTeacherSecondChoiceEnd());
            System.out.println();
            projectTime.updateById();
        }

        //6老师第三次选择时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 6));
        if (list.size() == 0){
            projectTime.setType(6);
            projectTime.setPtId(null);
            projectTime.setBeginTime(req.getTeacherThirdChoiceStart());
            projectTime.setEndTime(req.getTeacherThirdChoiceEnd());
            projectTime.insertOrUpdate();
        }else{
            projectTime = list.get(0);
            projectTime.setBeginTime(req.getTeacherThirdChoiceStart());
            projectTime.setEndTime(req.getTeacherThirdChoiceEnd());
            System.out.println();
            projectTime.updateById();
        }

        //7项目最终提交时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 7));
        if (list.size() == 0){
            projectTime.setType(7);
            projectTime.setPtId(null);
            projectTime.setBeginTime(req.getProjectFinalStart());
            projectTime.setEndTime(req.getProjectFinalEnd());
            projectTime.insertOrUpdate();
        }else{
            projectTime = list.get(0);
            projectTime.setBeginTime(req.getProjectFinalStart());
            projectTime.setEndTime(req.getProjectFinalEnd());
            System.out.println();
            projectTime.updateById();
        }
        return ResponseEntity.ok(1);
    }

    @GetMapping
    public  ResponseEntity<TimeReq>  getTime(){
        List<ProjectTime> list = new ArrayList<>();
        TimeReq resp = new TimeReq();

        //1发布时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 1));
        resp.setPublishStart(list.get(0).getBeginTime());
        resp.setPublishEnd(list.get(0).getEndTime());

        //2学生第一次选择时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 2));
        resp.setStuFirstChoiceStart(list.get(0).getBeginTime());
        resp.setStuFirstChoiceEnd(list.get(0).getEndTime());

        //3教师第一次选择时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 3));
        resp.setTeacherFirstChoiceStart(list.get(0).getBeginTime());
        resp.setTeacherFirstChoiceEnd(list.get(0).getEndTime());

        //4学生第二次选择时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 4));
        resp.setStuSecondChoiceStart(list.get(0).getBeginTime());
        resp.setStuSecondChoiceEnd(list.get(0).getEndTime());

        //5教师第二次选择时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 5));
        resp.setTeacherSecondChoiceStart(list.get(0).getBeginTime());
        resp.setTeacherSecondChoiceEnd(list.get(0).getEndTime());

        //6教师第三次选择时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 6));
        resp.setTeacherThirdChoiceStart(list.get(0).getBeginTime());
        resp.setTeacherThirdChoiceEnd(list.get(0).getEndTime());

        //7项目结束时间
        list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 7));
        resp.setProjectFinalStart(list.get(0).getBeginTime());
        resp.setProjectFinalEnd(list.get(0).getEndTime());

        System.out.println(resp);
        return  ResponseEntity.ok(resp);
    }

}
