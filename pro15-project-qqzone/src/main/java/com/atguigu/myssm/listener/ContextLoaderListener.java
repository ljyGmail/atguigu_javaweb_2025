package com.atguigu.myssm.listener;

import com.atguigu.myssm.ioc.BeanFactory;
import com.atguigu.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName: ContextLoaderListener
 * Package: com.atguigu.myssm.listener
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 27. 오전 9:38
 * @Version 1.0
 */
// 监听上下文启动，在上下文启动的时候去创建IOC容器，然后将其保存到application作用域
// 后面中央控制器再从application作用域中去获取IOC容器
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 1. 获取ServletContext对象
        ServletContext application = sce.getServletContext();
        // 2. 获取上下文的初始化参数
        String path = application.getInitParameter("contextConfigLocation");
        // 3. 创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        // 4. 将IOC容器保存到application作用域
        application.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
