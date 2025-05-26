package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo02Servlet
 * Package: com.atguigu.servlet
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 26. 오후 3:41
 * @Version 1.0
 */
@WebServlet("/demo02.do")
public class Demo02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo02 service...");
        request.getRequestDispatcher("succ.html").forward(request, response);
    }
}
