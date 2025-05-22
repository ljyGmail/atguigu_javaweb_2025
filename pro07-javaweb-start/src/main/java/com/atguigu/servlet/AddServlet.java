package com.atguigu.servlet;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo
 * Package: com.atguigu.servlet
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 20. 오후 1:54
 * @Version 1.0
 */
public class AddServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        // GET方式目前不需要设置编码(基于Tomcat8)
        // 如果是GET请求发送的中文数据，转码稍微有点麻烦(Tomcat8之前)
        String fname = request.getParameter("fname");
        // 1. 将字符串打散成字节数组
        byte[] bytes = fname.getBytes("ISO8859-1");
        // 2. 将字节数组按照指定的编码重新组装成字符串
        fname = new String(bytes, "UTF-8");
         */
        // POST方式下，设置编码，防止中文乱码
        // 需要注意的是，设置编码这一句代码必须放在所有的获取参数的动作之前
        request.setCharacterEncoding("UTF-8");
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        FruitDAO fruitDAO = new FruitDAOImpl();
        boolean flag = fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));

        System.out.println(flag ? "添加成功!" : "添加失败");
    }
}
