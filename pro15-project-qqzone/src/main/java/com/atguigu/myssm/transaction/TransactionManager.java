package com.atguigu.myssm.transaction;

import com.atguigu.myssm.basedao.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName: TransactionManager
 * Package: com.atguigu.myssm.transaction
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 26. 오후 6:38
 * @Version 1.0
 */
public class TransactionManager {

    // 开启事务
    public static void beginTransaction() throws SQLException {
        ConnectionUtil.getConnection().setAutoCommit(false);
    }

    // 提交事务
    public static void commit() throws SQLException {
        Connection conn = ConnectionUtil.getConnection();
        conn.commit();
        // conn.close();
        ConnectionUtil.closeConnection();
    }

    // 回滚事务
    public static void rollback() throws SQLException {
        Connection conn = ConnectionUtil.getConnection();
        conn.rollback();
        ConnectionUtil.closeConnection();
    }
}
