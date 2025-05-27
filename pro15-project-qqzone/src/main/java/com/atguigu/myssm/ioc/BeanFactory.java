package com.atguigu.myssm.ioc;

/**
 * ClassName: BeanFactory
 * Package: com.atguigu.myssm.io
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 26. 오후 1:49
 * @Version 1.0
 */
public interface BeanFactory {
    Object getBean(String beanId);
}
