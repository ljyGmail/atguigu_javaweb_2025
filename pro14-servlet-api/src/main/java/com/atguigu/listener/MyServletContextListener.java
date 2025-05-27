package com.atguigu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ClassName: MyServletContextListener
 * Package: com.atguigu.listener
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오전 9:31
 * @Version 1.0
 */
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Servlet上下文对象初始化的动作被我监听到了...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servlet上下文对象销毁的动作被我监听到了...");

    }
}
