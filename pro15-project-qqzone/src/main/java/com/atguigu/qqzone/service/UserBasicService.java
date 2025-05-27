package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: UserBasicService
 * Package: com.atguigu.qqzone.service
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:55
 * @Version 1.0
 */
public interface UserBasicService {

    UserBasic login(String loginId, String pwd);

    List<UserBasic> getFriendList(UserBasic userBasic);
}
