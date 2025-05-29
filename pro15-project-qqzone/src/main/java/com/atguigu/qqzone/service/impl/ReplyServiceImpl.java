package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;

import java.util.List;

/**
 * ClassName: ReplyServiceImpl
 * Package: com.atguigu.qqzone.service.impl
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 29. 오전 11:08
 * @Version 1.0
 */
public class ReplyServiceImpl implements ReplyService {
    private ReplyDAO replyDAO;
    // 此处引入的是其他POJO对应的Service接口，而不是DAO接口
    // 其他POJO对应的业务逻辑是封装在Service层的，我需要调用别人的业务逻辑方法，而不去考虑其他业务内部的细节。
    private HostReplyService hostReplyService;

    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }
}
