package com.practice.controller;

import com.practice.dto.BaseResp;
import com.practice.entity.Project;
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


    @GetMapping("/{teacherId}")
    public ResponseEntity<List<Project>> queryAllMyProject(@PathVariable("teacherId") Integer teacherId){
        return ResponseEntity.ok(this.projectService.queryAllMyProject(teacherId));
    }

    @PutMapping("/{id}")
    public ResponseEntity publish(@PathVariable("id") Integer pId){
        return ResponseEntity.ok(projectService.publish(pId));
    }
}
