package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * ClassName: ReplyController
 * Package: com.atguigu.qqzone.controller
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 31. 오전 9:54
 * @Version 1.0
 */
public class ReplyController {

    private ReplyService replyService;

    public String addReply(String content, Integer topicId, HttpSession session) {

        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(content, LocalDateTime.now(), author, new Topic(topicId));

        replyService.addReply(reply);

        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }
}
