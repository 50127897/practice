package com.practice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.practice.dto.BaseResp;
import com.practice.dto.ProjectStudent;
import com.practice.dto.ResponseJsonEntity;
import com.practice.dto.ResultResp;
import com.practice.entity.Choice;
import com.practice.entity.Member;
import com.practice.mapper.ChoiceMapper;
import com.practice.mapper.ProjectMapper;
import com.practice.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProjectService {

    public static final String firstAdd = "first = first + 1";

    public static final String secondAdd = "second = second + 1";

    public static final String thirdAdd = "third = third + 1";

    public static final String firstSub = "first = first - 1";

    public static final String secondSub = "second = second - 1";

    public static final String thirdSub = "third = third - 1";

    public static final Project PROJECT = new Project();

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ChoiceMapper choiceMapper;

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

    public ResponseEntity delete(Integer pid) {
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


    public ResponseEntity getStuChoiceOnProject(ProjectStudent projectStudent) {
        IPage<Choice> iPage;
        Integer type = projectStudent.getType();
        boolean typeFlag = type==null?false:true;
        iPage = new Choice().selectPage(new Page(projectStudent.getCurrent(),projectStudent.getSize()),new QueryWrapper<Choice>().eq("p_id", projectStudent.getPId()).eq(typeFlag,"type", type));
        iPage.getRecords().forEach(choice -> {
            Member member =(Member) new Member().selectById(choice.getMId());
            choice.setMember(member);
        });
        return ResponseEntity.ok(iPage);
    }

    public ResponseEntity getStudentInfo(ProjectStudent projectStudent) {
        Project project = (Project) new Project().selectOne(new QueryWrapper<Project>().eq("p_id", projectStudent.getPId()));
        List<Member> members = new LinkedList<>();
        Integer type = projectStudent.getType();
        if(project.getSelectedIds() == null){
            return ResponseEntity.ok(null);
        }
        JSON.parseObject(project.getSelectedIds(), new TypeReference<List<Integer>>(){}).forEach(
                id -> members.add((Member) new Member().selectById((Integer) id))
        );
        return ResponseEntity.ok(members);
    }


    public ResponseEntity choiceProject(List<Choice> choices) {
        choices.forEach(choice -> {
            if(choice.getCId()!=null){
                Choice oldChoice = choiceMapper.selectById(choice.getCId());
                if(choice.getType() == oldChoice.getType() && choice.getPId() == oldChoice.getPId() && choice.getMId() == oldChoice.getMId()){
                    //志愿不变，跳过
                    return ;
                }
                //志愿改变，原先的志愿项目相应的志愿人数减一
                switch (oldChoice.getType()){
                    case 1: PROJECT.update(new UpdateWrapper<Project>().setSql(firstSub).eq("p_id",oldChoice.getPId())); break;
                    case 2: PROJECT.update(new UpdateWrapper<Project>().setSql(secondSub).eq("p_id",oldChoice.getPId())); break;
                    case 3: PROJECT.update(new UpdateWrapper<Project>().setSql(thirdSub).eq("p_id",oldChoice.getPId())); break;
                }
            }
            //所选项目相应志愿数加一
            switch (choice.getType()){
                case 1: PROJECT.update(new UpdateWrapper<Project>().setSql(firstAdd).eq("p_id",choice.getPId())); break;
                case 2: PROJECT.update(new UpdateWrapper<Project>().setSql(secondAdd).eq("p_id",choice.getPId())); break;
                case 3: PROJECT.update(new UpdateWrapper<Project>().setSql(thirdAdd).eq("p_id",choice.getPId())); break;
            }
        });
        choices.forEach(Model::insertOrUpdate);
        return ResponseJsonEntity.ok("志愿提交成功");
    }

    public ResponseEntity chooseStudent(List<Choice> list) {
        list.forEach(choice -> {
            List<Integer> idsList;
            //member更改
            Member member = (Member) new Member().selectById(choice.getMId());
            Project project = (Project) new Project().selectById(choice.getPId());
            member.setProjectId(choice.getPId());
            member.setProjectName(project.getPName());
            member.setSelected(1);
            member.setTeacherId(project.getTeacherId());
            member.insertOrUpdate();
            //project更改 添加学生id
            String ids = project.getSelectedIds();
            if(ids==null){
//                project.setSelectedIds(member.getMId()+"");
                idsList = new ArrayList<>();
            }else {
                idsList = JSON.parseObject(project.getSelectedIds(), new TypeReference<List<Integer>>() {});
//                project.setSelectedIds(ids+","+member.getMId());
            }

            idsList.add(member.getMId());
            project.setSelectedIds(JSON.toJSONString(idsList));
            //判断是否满人
            project.setSelected(project.getSelected()+1);
            if(project.getSelected() == project.getMember()){
                project.setIsFull(1);
            }
            project.insertOrUpdate();
            List<Choice> memberChoices = choiceMapper.selectList(new QueryWrapper<Choice>().eq("m_id", member.getMId()));
            if (!ObjectUtils.isEmpty(memberChoices)){
                memberChoices.forEach(memberChoice->{
                    //项目中原先的志愿相应的志愿人数减一
                    switch (memberChoice.getType()){
                        case 1: PROJECT.update(new UpdateWrapper<Project>().setSql(firstSub).eq("p_id",memberChoice.getPId())); break;
                        case 2: PROJECT.update(new UpdateWrapper<Project>().setSql(secondSub).eq("p_id",memberChoice.getPId())); break;
                        case 3: PROJECT.update(new UpdateWrapper<Project>().setSql(thirdSub).eq("p_id",memberChoice.getPId())); break;
                    }
                    //删除志愿表记录
                    memberChoice.deleteById();
                });
            }

        });
        //TODO 发送邮箱通知学生
        return ResponseEntity.ok(1);
    }

    public ResponseEntity<ResultResp> getResult(Integer mid) {
        Member member = (Member) new Member().selectById(mid);
        if (member.getTeacherId() == null){
            return ResponseEntity.ok(null);
        }
        Member teacher = (Member) new Member().selectById(member.getTeacherId());
        Project project = this.projectMapper.selectById(member.getProjectId());
        ResultResp resultResp = new ResultResp();
        resultResp.setAddress(teacher.getAddress());
        resultResp.setName(teacher.getName());
        resultResp.setPhone(teacher.getPhone());
        resultResp.setEmail(teacher.getEmail());
        resultResp.setContent(project.getContent());
        resultResp.setFile(project.getFile());
        resultResp.setPName(project.getPName());
        return ResponseEntity.ok(resultResp);
    }
}
