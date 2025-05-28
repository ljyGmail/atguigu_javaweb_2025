package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: UserController
 * Package: com.atguigu.qqzone.controller
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 4:07
 * @Version 1.0
 */
public class UserController {

    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String loginId, String pwd, HttpSession session) {
        // 1. 登录验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if (userBasic != null) {
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            session.setAttribute("userBasic", userBasic);
            return "index";
        } else {
            return "login";
        }
    }
}
