package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.dao.UserBasicDAO;

import java.util.List;

/**
 * ClassName: UserBasicDAOImpl
 * Package: com.atguigu.qqzone.pojo.dao.impl
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:43
 * @Version 1.0
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {

        return load("select * from t_user_basic where login_id = ? and pwd = ?", loginId, pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "select fid from t_friend where uid = ?";
        return executeQuery(sql, userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return load("select * from t_user_basic where id = ?", id);
    }
}
