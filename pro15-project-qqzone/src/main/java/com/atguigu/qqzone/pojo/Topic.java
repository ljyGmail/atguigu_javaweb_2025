package com.atguigu.qqzone.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: Topic
 * Package: com.atguigu.qqzone.pojo
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:21
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Topic {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime topicDate;

    private UserBasic author; // N:1
    private List<Reply> replyList; // 1:N

    public Topic(Integer id) {
        this.id = id;
    }
}
