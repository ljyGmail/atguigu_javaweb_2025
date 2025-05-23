package com.atguigu.fruit.servlets;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: UpdateServlet
 * Package: com.atguigu.fruit.servlets
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 23. 오후 2:51
 * @Version 1.0
 */
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDAO dao = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置编码
        request.setCharacterEncoding("UTF-8");

        // 2. 获取参数
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        // 3. 执行更新
        Fruit fruit = new Fruit(fid, fname, price, fcount, remark);
        dao.updateFruit(fruit);

        // 4. 资源跳转
        // processTemplate("index", request, response);
        // request.getRequestDispatcher("index.html").forward(request, response);
        // 此处需要重定向，目的是重现给IndexServlet发请求，重新获取fruitList，然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的。
        response.sendRedirect("index");
    }
}
