package com.atguigu.fruit.servlets;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * ClassName: FruitServlet
 * Package: com.atguigu.fruit.servlets
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 24. 오전 9:36
 * @Version 1.0
 */
@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        // 获取当前类中所有的方法
        Method[] methods = this.getClass().getDeclaredMethods();

        for (Method m : methods) {
            // 获取方法名称
            String methodName = m.getName();
            if (operate.equals(methodName)) {
                // 找到和operate同名的方法，通过反射技术调用它
                try {
                    m.invoke(this, request, response);
                    return;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new RuntimeException("operate值非法");
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        // 设置当前页，默认值1
        Integer pageNo = 1;

        String oper = request.getParameter("oper");
        // 如果oper != null，说明是通过表单的查询按钮点击过来的
        // 如果oper == null，说明不是通过表单的查询按钮点击过来的

        String keyword = null;
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            // 说明是点击表单查询发送过来的请求
            // 此时，pageNo应该还是1，keyword应该从请求参数重获取
            pageNo = 1;
            keyword = request.getParameter("keyword");
            // 如果keyword为null，需要设置为空字符串""，否则查询时会拼加成%null%，我们期望的是%%
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            // 将keyword保存(覆盖)到session中
            session.setAttribute("keyword", keyword);
        } else {
            // 说明不是点击表单查询发送过来的请求(比如点击上一页下一页按钮或者直接在地址栏输入网址)
            // 此时keyword应该从session作用域获取
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr); // 如果从请求中读取到pageNo，则类型转换，否则，pageNo默认就是1
            }
            // 如果不是点击的查询按钮，那么查询的是基于session中保存的现有keyword进行查询
            Object keywordObj = session.getAttribute("keyword");

            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        // 重新更新当前页的值
        session.setAttribute("pageNo", pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNo);
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
        int fruitCount = fruitDAO.getFruitCount(keyword);
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

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit(0, fname, price, fcount, remark);

        fruitDAO.addFruit(fruit);

        response.sendRedirect("fruit.do");
    }

    private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("add", request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);

            // processTemplate("index", request, response);
            response.sendRedirect("fruit.do");
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
            processTemplate("edit", request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        fruitDAO.updateFruit(fruit);

        // 4. 资源跳转
        // processTemplate("index", request, response);
        // request.getRequestDispatcher("index.html").forward(request, response);
        // 此处需要重定向，目的是重现给IndexServlet发请求，重新获取fruitList，然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的。
        response.sendRedirect("fruit.do");
    }
}
