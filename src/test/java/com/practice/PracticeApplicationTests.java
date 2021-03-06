package com.practice;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.config.Sha256;
import com.practice.mapper.*;
import com.practice.entity.*;
import com.practice.repository.MessageRepository;
import jdk.nashorn.internal.runtime.options.Option;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@SpringBootTest
class PracticeApplicationTests {

    @Autowired
    private MessageRepository msg;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ChoiceMapper choiceMapper;

    @Autowired
    private AnnounceMapper announceMapper;

    @Autowired
    private ProjectDocMapper project_docMapper;

    @Autowired
    private PmMapper pmMapper;


    @Test
    void demo(){
        System.out.println("ok");
    }

    @Test
    void contextLoads() {
        List<Message> select = msg.select(11);
        System.out.println(select);
        System.out.println(select.get(0));
//        List list = new ProjectDoc().selectList(new QueryWrapper<ProjectDoc>().eq("teacher_id", 11).le("type", 10));
//        System.out.println(list);
//        List list = new ProjectTime().selectList(new QueryWrapper<ProjectTime>().eq("type", 1));
//        System.out.println(list);
//        String projectPath = System.getProperty("user.dir");
//        System.out.println(projectPath);
//        String fileDirPath = projectPath.substring(0, projectPath.lastIndexOf('\\') + 1) + "upload\\";
//        System.out.println(fileDirPath);
//        for (int i=1 ;i<10;i++) {
//            List<Member> list = memberMapper.selectAll();
//            System.out.println(list);
//            Member member = new Member();
//            member.setUsername("teacher"+i);
//            member.setName("李教师"+i);
//            member.setPassword("teacher"+i);
//            member.setType(3);
//            memberMapper.insertSelective(member);
//        }
    }

    @Test
    void contextLoads2() {
//        Project project = projectMapper.selectById(41);
//        new Project().update(new UpdateWrapper<Project>().setSql("first = first +1").eq("p_id",31));



//        for (int i=1 ;i<10;i++) {
//            Project project = new Project();
//            project.setContent("选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。" +
//                    "");
//            project.setMember(5);
//            project.setPName("综合实践管理系统888"+i);
//            project.setTeacherId(i+10);
//            project.setStatus(4);
//            project.setTeacherName("teacher"+i);
//            projectMapper.insert(project);
//        }
    }

    @Test
    void contextLoads3() {
//        List<Choice> list = this.choiceMapper.selectAll();
//        System.out.println(list);
//        for (int i=1 ;i<10;i++) {
//            Choice choice = new Choice();
//            choice.setMid(i+1);
//            choice.setPid(i);
//            choice.setType(i%3);
//            choiceMapper.insertSelective(choice);
//        }
    }

    @Test
    void contextLoads4() {
//        List<Announce> list = this.announceMapper.selectAll();
//        System.out.println(list);
//        for (int i=1 ;i<10;i++) {
//            Announce announce = new Announce();
//            announce.setContent("选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。" +
//                    "");
//            announce.setTitle("选课规则");
//            announce.setType(i%2);
//            announceMapper.insertSelective(announce);
//        }
    }

    @Test
    void contextLoads5() {
//        List<ProjectDoc> list = this.project_docMapper.selectAll();
//        System.out.println(list);
//        for (int i=1 ;i<10;i++) {
//            ProjectDoc doc = new ProjectDoc();
//            doc.setPdName("文档"+i);
//            doc.setStudentId(i+1);
//            doc.setTeacherName("teacher"+i);
//            doc.setStudentName("admin"+i);
//            doc.setTime(new Date());
//            doc.setType(i%6);
//            doc.setTeacherId(i+10);
//            project_docMapper.insertSelective(doc);


//        }
    }
    @Test
    void contextLoads6() {
        for (int i=1 ;i<10;i++) {
//            ProjectDoc doc = project_docMapper.selectByPrimaryKey(i);
//            System.out.println(doc);
////            doc.setUrl("www.baidu.com");
//            project_docMapper.updateByPrimaryKeySelective(doc);



        }
    }

    /**
     * 分页查询
     */
    @Test
    public void selectByPage() {
//        IPage<Project> iPage = new Project().selectPage(new Page<Project>(1, 2),
//                null
//        );
//        try {
//            iPage.setCurrent(3);
//            System.out.println(new ObjectMapper().writeValueAsString(iPage));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        /*
        {"records":[],"total":22,"size":2,"current":1,"pages":11}
         */
    }


    @Test
    void contextLoads7() {
//        List<Pm> list = this.pmMapper.selectAll();
//        System.out.println(list);
//        for (int i=1 ;i<10;i++) {
//            Pm pm = new Pm();
//            pm.setPid(i);
//            pm.setMid(i+1);
//            pmMapper.insertSelective(pm);

//        }
    }

    @Test
    void contextLoads8() {
//        String sha256 = Sha256.getSHA256("123456");
//        System.out.println(sha256);

//        String password = "8d969ee f6ecad3c 29a3a629 280e686c f0c3f5d5 a86aff3c a12020c9 23adc6c92";
        //sha256     8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
        //md5        dd6e 5e59 18e9 4d99 7c68 6fce bc56 922f
        //打印md5加密后的密码
        System.out.println("md5加密结果："+ DigestUtils.md5DigestAsHex("8d969eef6ecad3c29a3a629280e686c".getBytes()));
    }
    @Test
    void contextLoads9() {
//        List<Member> list = memberMapper.selectAll();
//        list.forEach(member -> {
//            String password = Sha256.getSHA256(member.getPassword());
//            String newPassWord = DigestUtils.md5DigestAsHex(password.getBytes());
//            member.setPassword(newPassWord);
//            memberMapper.updateByPrimaryKeySelective(member);
//
//        });
    }


}
