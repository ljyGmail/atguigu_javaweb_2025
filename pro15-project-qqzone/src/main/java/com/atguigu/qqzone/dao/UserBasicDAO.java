package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: UserBasicDAO
 * Package: com.atguigu.qqzone.pojo.dao
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:38
 * @Version 1.0
 */
public interface UserBasicDAO {
    // 根据用户和密码获取特定用户信息
    public UserBasic getUserBasic(String loginId, String pwd);

    // 获取特定用户的所有好友列表
    public List<UserBasic> getUserBasicList(UserBasic userBasic);

    // 根据id查询UserBasic的信息
    UserBasic getUserBasicById(Integer id);
}
