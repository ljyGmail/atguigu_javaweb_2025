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

    private FruitDAO fruitDAO = new FruitDAOImpl();

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (pageNo == null) {
            pageNo = 1;
        }

        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            // 将keyword保存(覆盖)到session中
            session.setAttribute("keyword", keyword);
        } else {
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

        // 总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        // 总页数
        int pageCount = (fruitCount + 5 - 1) / 5;
        session.setAttribute("pageCount", pageCount);

        return "index";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitDAO.addFruit(fruit);
        return "redirect:fruit.do";
    }

    private String addUI() {
        return "add";
    }

    private String delete(Integer fid) {
        if (fid != null) {
            fruitDAO.delFruit(fid);

            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(Integer fid, HttpServletRequest request) {
        if (fid != null) {
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {

        // 执行更新
        Fruit fruit = new Fruit(fid, fname, price, fcount, remark);
        fruitDAO.updateFruit(fruit);

        return "redirect:fruit.do";
    }
}
