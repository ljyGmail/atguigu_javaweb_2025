package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * ClassName: FruitDAOImpl
 * Package: com.atguigu.dao.impl
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 22. 오후 4:13
 * @Version 1.0
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return executeQuery("select * from t_fruit where fname like ? or remark like ? limit ?, 5", "%" + keyword + "%", "%" + keyword + "%", (pageNo - 1) * 5);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return load("select * from t_fruit where fid = ?", fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname = ?, price = ?, fcount = ?, remark = ? where fid = ?";
        executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        executeUpdate("delete from t_fruit where fid = ?", fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0, ?, ?, ?, ?)";
        executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
    }

    @Override
    public int getFruitCount(String keyword) {
        return ((Long) executeComplexQuery("select count(*) from t_fruit where fname like ? or remark like ?", "%" + keyword + "%", "%" + keyword + "%")[0]).intValue();
    }
}
