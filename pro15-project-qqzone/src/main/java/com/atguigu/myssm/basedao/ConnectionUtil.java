package com.atguigu.myssm.basedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ClassName: ConnectionUtil
 * Package: com.atguigu.myssm.basedao
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 26. 오후 6:41
 * @Version 1.0
 */
public class ConnectionUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/qqzonedb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
    public static final String PWD = "445566hh";

    private static Connection createConnection() {
        try {
            // 1. 加载驱动
            Class.forName(DRIVER);
            // 2. 通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = createConnection();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void closeConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null) {
            return;
        }
        if (!conn.isClosed()) {
            conn.close();
            threadLocal.set(null);
        }
    }
}
