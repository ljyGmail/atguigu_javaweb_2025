package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Reply;

import java.util.List;

/**
 * ClassName: ReplyService
 * Package: com.atguigu.qqzone.service
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 29. 오전 11:07
 * @Version 1.0
 */
public interface ReplyService {
    // 根据topic的id获取所有的回复
    List<Reply> getReplyListByTopicId(Integer topicId);
}
