package com.atguigu.fruit.controller;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: FruitController
 * Package: com.atguigu.fruit.controller
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 25. 오후 12:21
 * @Version 1.0
 */
public class FruitController {

    // 之前FruitServlet是一个Servlet组件，那么其中的init方法一定会被调用
    // 之前

    private FruitDAO fruitDAO = new FruitDAOImpl();

    private String index(HttpServletRequest request) {

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

        return "index";
    }

    private String add(HttpServletRequest request) {
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit(0, fname, price, fcount, remark);

        fruitDAO.addFruit(fruit);

        return "redirect:fruit.do";
    }

    private String addUI(HttpServletRequest request) {
        return "add";
    }

    private String delete(HttpServletRequest request) {
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);

            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(HttpServletRequest request) {
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String update(HttpServletRequest request) {
        // 1. 获取参数
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        // 2. 执行更新
        Fruit fruit = new Fruit(fid, fname, price, fcount, remark);
        fruitDAO.updateFruit(fruit);

        return "redirect:fruit.do";
    }
}
