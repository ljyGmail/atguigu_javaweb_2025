package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: TopicDAO
 * Package: com.atguigu.qqzone.pojo.dao
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:40
 * @Version 1.0
 */
public interface TopicDAO {
    // 获取指定用户的所有日志
    List<Topic> getTopicList(UserBasic userBasic);

    // 添加日志
    void addTopic(Topic topic);

    // 删除日志
    void delTopic(Topic topic);

    // 获取特定日志信息
    Topic getTopic(Integer id);
}
