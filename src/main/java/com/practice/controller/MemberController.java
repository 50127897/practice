package com.practice.controller;

import com.practice.pojo.Member;
import com.practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("login")
    public ResponseEntity<Member> Login(@RequestBody Member member){
        member.setPassword(DigestUtils.md5DigestAsHex(member.getPassword().getBytes()));
        Member loginMember = this.memberService.login(member);
        if(!ObjectUtils.isEmpty(loginMember)){
            return ResponseEntity.ok(loginMember);
        }
        return ResponseEntity.notFound().build();
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
