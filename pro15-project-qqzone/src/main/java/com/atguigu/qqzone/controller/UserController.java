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
            // 1-1 获取相关的好友信息
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            // 1-2 获取日志列表
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            // userBasic这个key保存的是登录者的信息
            session.setAttribute("userBasic", userBasic);
            // friend这个key保存的是当前进入的是谁的空间
            session.setAttribute("friend", userBasic);
            return "index";
        } else {
            return "login";
        }
    }

    public String friend(Integer id, HttpSession session) {
        // 根据id获取指定的用户信息
        UserBasic currentFriend = userBasicService.getUserBasicById(id);

        List<Topic> topicList = topicService.getTopicList(currentFriend);
        currentFriend.setTopicList(topicList);

        session.setAttribute("friend", currentFriend);

        return "index";
    }
}
