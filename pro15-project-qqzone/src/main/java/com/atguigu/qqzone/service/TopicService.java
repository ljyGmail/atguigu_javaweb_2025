package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: TopicService
 * Package: com.atguigu.qqzone.service
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 4:03
 * @Version 1.0
 */
public interface TopicService {
    // 查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);
}
