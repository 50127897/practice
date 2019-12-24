package com.practice.controller;

import com.practice.dto.*;
import com.practice.entity.Member;
import com.practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 人员controller
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * @param loginReq 登陆功能
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq) {
        if (loginReq.getUserName() == null || loginReq.getPassword() == null || loginReq.getType() == null) {
            return ResponseEntity.ok(BaseResp.fail("0004", "参数错误"));
        }


        loginReq.setPassword(DigestUtils.md5DigestAsHex(loginReq.getPassword().getBytes()));
        Member member = this.memberService.selectByUsername(loginReq.getUserName());
        if (member == null) {
            return ResponseEntity.ok(BaseResp.fail("0001", "找不到该用户"));
        } else if (!loginReq.getPassword().equals(member.getPassword())) {
            return ResponseEntity.ok(BaseResp.fail("0002", "密码错误"));
        } else if (!loginReq.getType().equals(member.getType())) {
            return ResponseEntity.ok(BaseResp.fail("0003", "用户类型错误，请重新选择管理员，教师，或学生"));
        }
        String token = memberService.saveToken(member);
        LoginResp resp = new LoginResp();
        resp.setMId(member.getMId());
        resp.setType(member.getType());
        resp.setToken(token);
        return ResponseEntity.ok(BaseResp.success(resp, "登陆成功"));
    }

    @GetMapping("/{mid}")
    public ResponseEntity getInfo(@PathVariable Integer mid) {
        Member member = this.memberService.getInfo(mid);
        if (ObjectUtils.isEmpty(member)) {
            return ResponseEntity.ok(BaseResp.fail("0001", "找不到用户"));
        }
        MemberResp resp = new MemberResp();
        resp.setAddress(member.getAddress());
        resp.setClasses(member.getClasses());
        resp.setCollege(member.getCollege());
        resp.setGrade(member.getGrade());
        resp.setMajor(member.getMajor());
        resp.setName(member.getName());
        resp.setPhone(member.getPhone());
        resp.setUserName(member.getUserName());
        resp.setEmail(member.getEmail());

        return ResponseEntity.ok(BaseResp.success(resp));
    }

    @PutMapping
    public ResponseEntity<BaseResp> update(@RequestBody Member member) {
        Integer result = this.memberService.updateByPrimaryKey(member);

        return result == 1 ? ResponseEntity.ok(BaseResp.success("修改成功")):ResponseEntity.ok(BaseResp.fail("0001","修改失败"));
    }

    @RequestMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String token = request.getHeader("x-auth-token");
        if (token != null) {
            memberService.logout(token);
        }
        return ResponseJsonEntity.ok("成功退出");
    }

}
