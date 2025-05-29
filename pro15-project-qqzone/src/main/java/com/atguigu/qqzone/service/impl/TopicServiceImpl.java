package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.TopicService;

import java.util.List;

/**
 * ClassName: TopicServiceImpl
 * Package: com.atguigu.qqzone.service.impl
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 4:04
 * @Version 1.0
 */
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO;
    // 此处引用的是replyService，而不是replyDAO
    private ReplyService replyService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        List<Reply> replyList = replyService.getReplyListByTopicId(id);
        topic.setReplyList(replyList);
        return topic;
    }
}
