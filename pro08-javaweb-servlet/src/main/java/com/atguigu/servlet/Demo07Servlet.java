package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo07
 * Package: com.atguigu.servlet
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 22. 오후 1:38
 * @Version 1.0
 */
public class Demo07Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo07...");
    }
}
