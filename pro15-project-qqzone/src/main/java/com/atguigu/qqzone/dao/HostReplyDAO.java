package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyDAO
 * Package: com.atguigu.qqzone.pojo.dao
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:43
 * @Version 1.0
 */
public interface HostReplyDAO {
    // 根据replyId查询关联的HostReply实体
    HostReply getHostReplyByReplyId(Integer replyId);
}
