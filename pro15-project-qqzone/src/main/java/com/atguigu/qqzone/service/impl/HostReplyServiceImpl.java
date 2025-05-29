package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.HostReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.service.HostReplyService;

/**
 * ClassName: HostReplyServiceImpl
 * Package: com.atguigu.qqzone.service.impl
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 29. 오전 11:14
 * @Version 1.0
 */
public class HostReplyServiceImpl implements HostReplyService {
    private HostReplyDAO hostReplyDAO;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }
}
