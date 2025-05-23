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
    public List<Fruit> getFruitList() {
        return executeQuery("select * from t_fruit");
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

}
