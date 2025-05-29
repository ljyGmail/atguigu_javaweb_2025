package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.dao.TopicDAO;

import java.util.List;

/**
 * ClassName: TopicDAOImpl
 * Package: com.atguigu.qqzone.pojo.dao.impl
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:51
 * @Version 1.0
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return executeQuery("select * from t_topic where author = ?", userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {

    }

    @Override
    public Topic getTopic(Integer id) {
        return load("select * from t_topic where id = ?", id);
    }
}
