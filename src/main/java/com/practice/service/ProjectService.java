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
import com.practice.entity.Choice;
import com.practice.entity.Member;
import com.practice.mapper.ChoiceMapper;
import com.practice.mapper.ProjectMapper;
import com.practice.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProjectService {

    public static final String firstAdd = "first = first + 1";

    public static final String secondAdd = "second = second + 1";

    public static final String thirdAdd = "third = third + 1";

    public static final String firstDelete = "first = first - 1";

    public static final String secondDelete = "second = second - 1";

    public static final String thirdDelete = "third = third - 1";

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
                    case 1: PROJECT.update(new UpdateWrapper<Project>().setSql(firstDelete).eq("p_id",oldChoice.getPId())); break;
                    case 2: PROJECT.update(new UpdateWrapper<Project>().setSql(secondDelete).eq("p_id",oldChoice.getPId())); break;
                    case 3: PROJECT.update(new UpdateWrapper<Project>().setSql(thirdDelete).eq("p_id",oldChoice.getPId())); break;
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
}
