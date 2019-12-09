package com.practice.controller;

import com.practice.Entiiy.Announce;
import com.practice.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/announce")
public class AnnounceController {

    @Autowired
    private AnnounceService announceService;

    @RequestMapping("/findAnnounce")
    public List<Announce> SelectAnnounce(@RequestParam(required = false) Integer type,@RequestParam(required = false) String title){
        return announceService.selectAnnounce(type,title);
    }

    @RequestMapping("/deleteById")
    public int deleteById(Integer id){
         return announceService.deleteById(id);
    }

    @PostMapping("/addAnnounce")
    public int addAnnounce(Announce announce){
        return announceService.addAnnounce(announce);
    }

}
