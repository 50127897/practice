package com.practice.controller;

import com.practice.dto.BaseResp;
import com.practice.dto.LoginReq;
import com.practice.dto.LoginResp;
import com.practice.entity.Member;
import com.practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 人员controller
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * @param loginReq
     * 登陆功能
     * @return
     */
    @PostMapping("login")
    public BaseResp Login(@RequestBody LoginReq loginReq){
        if(loginReq.getUsername() == null || loginReq.getPassword() == null || loginReq.getType() == null){
            return BaseResp.fail("0004","参数错误");
        }


        loginReq.setPassword(DigestUtils.md5DigestAsHex(loginReq.getPassword().getBytes()));
        Member member= this.memberService.selectByUsername(loginReq.getUsername());
        if(member == null){
            return BaseResp.fail("0001","找不到该用户");
        }else if(!loginReq.getPassword().equals(member.getPassword())){
            return BaseResp.fail("0002","密码错误");
        }else if(loginReq.getType() != member.getType()){
            return BaseResp.fail("0003","用户类型错误，请重新选择管理员，教师，或学生");
        }
        LoginResp resp = new LoginResp();
        resp.setMId(member.getMId());
        resp.setType(member.getType());
        return BaseResp.success(resp,"登陆成功");
//        member.setPassword(DigestUtils.md5DigestAsHex(member.getPassword().getBytes()));
//        Member loginMember = this.memberService.login(member);
//        if(!ObjectUtils.isEmpty(loginMember)){
//            return ResponseEntity.ok(loginMember);
//        }
//        return ResponseEntity.notFound().build();
    }

    @RequestMapping("/getInfo")
    public ResponseEntity<Member> getInfo(Integer mid){
        Member member =this.memberService.getInfo(mid);
        if(ObjectUtils.isEmpty(member)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(member);
    }

    @RequestMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody Member member){
        Integer result = this.memberService.updateByPrimaryKey(member);
        if(result > 0) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(new Integer(0));
    }

}
