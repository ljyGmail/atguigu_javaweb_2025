package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo05Servlet
 * Package: com.atguigu.servlet
 * Description:
 * 演示从HttpSession保存作用域中获取数据
 *
 * @Author ljy
 * @Create 2025. 5. 22. 오후 1:14
 * @Version 1.0
 */
public class Demo05Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object unameObj = request.getSession().getAttribute("uname");
        System.out.println(unameObj);
    }
}
