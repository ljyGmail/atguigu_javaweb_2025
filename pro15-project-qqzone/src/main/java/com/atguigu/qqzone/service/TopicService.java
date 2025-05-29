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

    Topic getTopicById(Integer id);

    // 根据id获取指定的topic信息，包含这个topic关联的作者信息
    Topic getTopic(Integer id);
}
