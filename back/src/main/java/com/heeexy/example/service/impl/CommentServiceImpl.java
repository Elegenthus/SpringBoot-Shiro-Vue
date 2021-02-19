package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.CommentDao;
import com.heeexy.example.service.CommentService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;

    @Override
    public JSONObject analyseComment(JSONObject jsonObject) {
        System.out.println("hahahha   " + jsonObject.toString());
        System.out.println("This is the word: " + jsonObject.getString("word"));
        System.out.println("This is the paragraph: " + jsonObject.getString("paragraph"));
        JSONObject newJson = new JSONObject();
        newJson.put("myAnswers", "this is my answer");
        newJson.put("atitude", "positive");
        newJson.put("percentage", 70);
        return CommonUtil.successJson(newJson);
    }

    @Override
    public JSONObject postFeedback(JSONObject jsonObject) {
        System.out.println("This is the suggestions from users: " + jsonObject.getString("content"));
        commentDao.writeFeedback(jsonObject);
        JSONObject newJson = new JSONObject();
        newJson.put("msg", "success");
        return CommonUtil.successJson(newJson);
    }
}
