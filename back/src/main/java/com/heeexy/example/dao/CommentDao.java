package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

public interface CommentDao {
    int writeFeedback(JSONObject jsonObject);
}
