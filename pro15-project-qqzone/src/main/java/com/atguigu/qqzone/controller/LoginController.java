package com.atguigu.qqzone.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: LoginController
 * Package: com.atguigu.qqzone.controller
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 28. 오전 9:35
 * @Version 1.0
 */
public class LoginController {

    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "login";
    }
}
