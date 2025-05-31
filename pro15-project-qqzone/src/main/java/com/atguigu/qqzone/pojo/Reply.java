package com.atguigu.qqzone.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: Reply
 * Package: com.atguigu.qqzone.pojo
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:21
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Reply {
    private Integer id;
    private String content;
    private LocalDateTime replyDate;

    private UserBasic author; // N:1
    private Topic topic; // N:1
    private HostReply hostReply; // 1:1

    public Reply(Integer id) {
        this.id = id;
    }

    public Reply(String content, LocalDateTime replyDate, UserBasic author, Topic topic) {
        this.content = content;
        this.replyDate = replyDate;
        this.author = author;
        this.topic = topic;
    }
}
