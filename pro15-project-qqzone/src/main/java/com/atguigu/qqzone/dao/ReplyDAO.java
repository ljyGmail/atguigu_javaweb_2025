package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

/**
 * ClassName: ReplyDAO
 * Package: com.atguigu.qqzone.pojo.dao
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:41
 * @Version 1.0
 */
public interface ReplyDAO {
    // 获取指定日志的回复列表
    List<Reply> getReplyList(Topic topic);

    // 添加回复
    void addReply(Reply reply);

    // 删除回复
    void delReply(Integer id);
}
