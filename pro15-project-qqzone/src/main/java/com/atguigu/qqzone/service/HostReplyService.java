package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyService
 * Package: com.atguigu.qqzone.service.impl
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 29. 오전 11:14
 * @Version 1.0
 */
public interface HostReplyService {

    HostReply getHostReplyByReplyId(Integer replyId);
}
