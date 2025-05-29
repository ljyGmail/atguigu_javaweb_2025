package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.HostReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyDAOImpl
 * Package: com.atguigu.qqzone.dao.impl
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 29. 오전 11:16
 * @Version 1.0
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return load("select * from t_host_reply where reply = ?", replyId);
    }
}
