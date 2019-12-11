package com.practice.controller;

import com.practice.entity.Project;
import com.practice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/addProject")
    public ResponseEntity<Integer> addProject(@RequestBody Project project){
        System.out.println(project);
        Integer result = this.projectService.addProject(project);
        return  ResponseEntity.ok(result);
    }

    @RequestMapping("/QueryAllMyProject")
    public ResponseEntity<List<Project>> QueryAllMyProject(Integer teacherid){
        System.out.println(teacherid);
        List<Project> list = this.projectService.QueryAllMyProject(teacherid);
        System.out.println(list);
        return ResponseEntity.ok(list);
    }


}
