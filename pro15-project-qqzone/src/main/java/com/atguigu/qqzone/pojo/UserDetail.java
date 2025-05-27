package com.atguigu.qqzone.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * ClassName: UserDetail
 * Package: com.atguigu.qqzone.pojo
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오후 3:21
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class UserDetail {
    private Integer id;
    private String realName;
    private String tel;
    private String email;
    private Date birth;
    private String star;
}

// 父类: java.util.Date 年月日时分秒毫秒
// 子类: java.sql.Date 年月日
// 子类: java.sql.Time 时分秒
