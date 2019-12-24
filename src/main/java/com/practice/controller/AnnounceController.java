package com.practice.controller;


import com.practice.dto.AnnounceResp;
import com.practice.dto.BaseResp;
import com.practice.entity.Announce;
import com.practice.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

/**
 * 通知controller
 */
@RestController
@RequestMapping("/announce")
public class AnnounceController {

    @Autowired
    private AnnounceService announceService;

    /**
     * @param type 类型 1管理员 2教师 3学生
     * @param title 根据通知标题模糊查询
     * @return
     */
    @GetMapping
    public BaseResp<List<AnnounceResp>> SelectAnnounce(@RequestParam(required = false) Integer type,
                                                       @RequestParam(required = false) String title){
        List<Announce> list = announceService.selectAnnounce(type, title);
        ArrayList<AnnounceResp> resps = new ArrayList<>();
        list.forEach(announce -> {
            AnnounceResp resp = new AnnounceResp();
            resp.setAId(announce.getAId());
            resp.setTime(announce.getCreateTime());
            resp.setTitle(announce.getTitle());
            resp.setContent(announce.getContent());
            resps.add(resp);
        });
        return BaseResp.success(resps);
    }

    /**
     * @param id 根据通知id删除通知信息
     * @return
     */
    @DeleteMapping("/{id}")
    public BaseResp deleteById(@PathVariable Integer id){
        if(announceService.deleteById(id) > 0){
            return BaseResp.success("删除成功");
        }
        return BaseResp.fail("400","删除失败");
    }

    /**
     * @param announce 添加通知信息
     * @return
     */
    @PostMapping
    public BaseResp addAnnounce(Announce announce){
        if(announceService.addAnnounce(announce) >0){
            return BaseResp.success("添加成功");
        }
        return BaseResp.fail("400","添加失败");
    }

}
