package com.atguigu.myssm.util;

/**
 * ClassName: StringUtil
 * Package: com.atguigu.myssm.util
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 23. 오후 2:15
 * @Version 1.0
 */
public class StringUtil {

    // 判断字符串是否为null或者""
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
