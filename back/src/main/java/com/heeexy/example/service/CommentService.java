package com.heeexy.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public interface CommentService {

    JSONObject analyseComment(JSONObject jsonObject);
    JSONObject postFeedback(JSONObject jsonObject);
}
