package com.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.dto.ResponseJsonEntity;
import com.practice.entity.ProjectDoc;
import com.sun.tools.internal.ws.wsdl.document.http.HTTPUrlEncoded;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;


/**
 * @author user
 */
@RequestMapping("/file")
@Controller
public class FileController {

    private final String projectPath = System.getProperty("user.dir");

    private final String fileDirPath = projectPath.substring(0, projectPath.lastIndexOf('/') + 1) + "upload/";

    @ResponseBody
    @PostMapping
    public ResponseEntity importData(@RequestParam(value = "file") MultipartFile file, HttpServletRequest req) throws IOException {

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return ResponseJsonEntity.badRequest("上传的文件有误");
        }

        int studentId = Integer.parseInt(req.getParameter("studentId"));
        int type = Integer.parseInt(req.getParameter("type"));

        String filePath = fileDirPath + studentId + "/" + type + "/" + fileName;
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            Files.createDirectories(newFile.toPath());
        }

        ProjectDoc projectDoc = (ProjectDoc) new ProjectDoc().selectOne(new QueryWrapper<ProjectDoc>().eq("student_id", studentId).eq("type", type));
        if (projectDoc == null) {
            file.transferTo(newFile);
            new ProjectDoc(studentId, type, fileName).insert();
        } else {
            file.transferTo(newFile);
            projectDoc.setPdName(fileName);
            projectDoc.updateById();
        }
        return ResponseJsonEntity.ok("上传成功");
    }

    @GetMapping
    public void getFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int type = Integer.parseInt(request.getParameter("type"));

        ProjectDoc projectDoc = (ProjectDoc) new ProjectDoc().selectOne(new QueryWrapper<ProjectDoc>().eq("student_id", studentId).eq("type", type));
        if (projectDoc == null) {
            outputStream.write(((String) Objects.requireNonNull(ResponseJsonEntity.badRequest("Illegal param").getBody())).getBytes());
        } else {
            String filePath = fileDirPath + studentId + "/" + type + "/" + projectDoc.getPdName();
            response.setHeader("Content-Type","application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition","attachment; filename="+ URLEncoder.encode(projectDoc.getPdName(),"UTF-8"));
            byte[] bytes = new byte[1024];
            try (FileInputStream fileInputStream = new FileInputStream(new File(filePath))) {
                int i = 0;
                while (i != -1) {
                    i = fileInputStream.read(bytes);
                    outputStream.write(bytes);
                }
            }
        }
        outputStream.close();
    }
}
