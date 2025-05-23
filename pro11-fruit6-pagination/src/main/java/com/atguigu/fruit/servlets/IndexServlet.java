package com.atguigu.fruit.servlets;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: IndexServlet
 * Package: com.atguigu.fruit.servlets
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 22. 오후 4:15
 * @Version 1.0
 */
// Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo = 1;
        String pageNoStr = request.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)) {
            pageNo = Integer.parseInt(pageNoStr);
        }

        HttpSession session = request.getSession();
        session.setAttribute("pageNo", pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(pageNo);
        // 保存到session作用域
        session.setAttribute("fruitList", fruitList);

        /*
        总记录条数   总页数
        1           1
        5           1
        6           2
        10          2
        11          3
        fruitCount  (fruitCount + 5 - 1) / 5
         */
        // 总记录条数
        int fruitCount = fruitDAO.getFruitCount();
        // 总页数
        int pageCount = (fruitCount + 5 - 1) / 5;
        session.setAttribute("pageCount", pageCount);

        // 此处的视图名称是index
        // 那么Thymeleaf会将这个 逻辑视图名称 对应到 物理视图名称 上去
        // 逻辑视图名称: index
        // 物理视图名称: view-prefix + 逻辑视图名称 + view-suffix
        // 所以真实的视图名称是: /         index        .html
        super.processTemplate("index", request, response);
    }
}
