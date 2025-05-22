package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo04Servlet
 * Package: com.atguigu.servlet
 * Description:
 * 演示向HttpSession保存数据
 *
 * @Author ljy
 * @Create 2025. 5. 22. 오후 1:13
 * @Version 1.0
 */
public class Demo04Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("uname", "lina");
    }
}
