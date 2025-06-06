package com.atguigu.fruit.service.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.fruit.service.FruitService;
import com.atguigu.myssm.basedao.ConnectionUtil;

import java.util.List;

/**
 * ClassName: FruitServiceImpl
 * Package: com.atguigu.biz.impl
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 26. 오후 1:21
 * @Version 1.0
 */
public class FruitServiceImpl implements FruitService {
    private FruitDAO fruitDAO;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        System.out.println("getFruitList -> " + ConnectionUtil.getConnection());
        return fruitDAO.getFruitList(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        System.out.println("getPageCount -> " + ConnectionUtil.getConnection());
        int count = fruitDAO.getFruitCount(keyword);
        return (count + 5 - 1) / 5;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
