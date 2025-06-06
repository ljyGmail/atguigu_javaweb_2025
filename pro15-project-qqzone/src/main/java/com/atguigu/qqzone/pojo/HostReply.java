package com.atguigu.qqzone.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: HostReply
 * Package: com.atguigu.qqzone.pojo
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class HostReply {
    private Integer id;
    private String content;
    private LocalDateTime hostReplyDate;

    private UserBasic author; // N:1
    private Reply reply; // 1:1

    public HostReply(Integer id) {
        this.id = id;
    }
}
