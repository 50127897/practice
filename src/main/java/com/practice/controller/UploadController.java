package com.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.dto.ResponseJsonEntity;
import com.practice.entity.ProjectDoc;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 * @author user
 */
@RequestMapping("/upload")
@RestController
public class UploadController {

    @PostMapping
    public ResponseEntity importData(@RequestParam(value = "file") MultipartFile file, HttpServletRequest req) throws IOException {

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return ResponseJsonEntity.badRequest("上传的文件有误");
        }

        int studentId = Integer.parseInt(req.getParameter("studentId"));
        int type = Integer.parseInt(req.getParameter("type"));

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath.substring(0,projectPath.lastIndexOf('/') + 1) + "upload/"+ studentId + "/" + type + "/" + fileName;
        File newFile = new File(filePath);
        if(!newFile.exists()){
            Files.createDirectories(newFile.toPath());
        }

        ProjectDoc projectDoc = (ProjectDoc) new ProjectDoc().selectOne(new QueryWrapper<ProjectDoc>().eq("student_id",studentId).eq("type",type));
        if(projectDoc == null){
            file.transferTo(newFile);
            new ProjectDoc(studentId, type, fileName).insert();
        }else{
            file.transferTo(newFile);
            projectDoc.setPdName(fileName);
            projectDoc.updateById();
        }
        return ResponseJsonEntity.ok("上传成功");
    }
}
