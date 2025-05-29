package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;

/**
 * ClassName: TopicController
 * Package: com.atguigu.qqzone.controller
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 29. 오전 10:59
 * @Version 1.0
 */
public class TopicController {

    private TopicService topicService;

    public String topicDetail(Integer id, HttpSession session) {
        Topic topic = topicService.getTopicById(id);
        session.setAttribute("topic", topic);

        return "frames/detail";
    }
}
