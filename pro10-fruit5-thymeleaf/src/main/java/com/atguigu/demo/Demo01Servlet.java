package com.atguigu.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo01Servlet
 * Package: com.atguigu.demo
 * Description:
 * 演示request保存作用域(demo01和demo02)
 *
 * @Author: ljy
 * @Create: 2025. 5. 22. 오후 11:53
 * @Version 1.0
 */
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 向request保存作用域保存数据
        request.setAttribute("uname", "lili");
        // 2. 客户端重定向
        // response.sendRedirect("demo02");

        // 3. 服务器端转发
        request.getRequestDispatcher("/demo02").forward(request, response);
    }
}
