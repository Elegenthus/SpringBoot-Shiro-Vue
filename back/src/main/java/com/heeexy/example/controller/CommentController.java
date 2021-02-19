package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.CommentService;
import com.heeexy.example.service.LoginService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping (value="/addComment")
    public JSONObject analyseComment(@RequestBody JSONObject requestJson){
//        CommonUtil.hasAllRequired(requestJson, "username,password");
        return commentService.analyseComment(requestJson);
    }

    @PostMapping(value = "/feedback")
    public JSONObject postFeedback(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "content");
        return commentService.postFeedback(requestJson);
    }
}
