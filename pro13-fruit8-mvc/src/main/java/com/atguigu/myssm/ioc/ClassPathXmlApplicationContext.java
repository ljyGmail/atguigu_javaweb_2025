package com.atguigu.myssm.ioc;

import com.atguigu.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ClassPathXmlApplicationContext
 * Package: com.atguigu.myssm.io
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 26. 오후 1:50
 * @Version 1.0
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();
    private String path = "applicationContext.xml";

    public ClassPathXmlApplicationContext() {
        this("applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path) {
        if (StringUtil.isEmpty(path)) {
            throw new RuntimeException("IOC容器的配置文件没有指定...");
        }
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            // 1. 创建DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 2. 创建DocumentBuilder对象
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 3. 创建Document对象
            Document document = builder.parse(is);
            // 4. 获取所有的bean节点
            NodeList beanList = document.getElementsByTagName("bean");

            for (int i = 0; i < beanList.getLength(); i++) {
                Node beanNode = beanList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    // 创建bean实例
                    Object beanObj = Class.forName(className).newInstance();
                    // 将bean实例对象保存到map容器中
                    beanMap.put(beanId, beanObj);
                    // 到目前为止，此处需要注意的是bean和bean之间的依赖关系还没有设置
                }
            }

            // 5. 组装bean之间的依赖关系
            for (int i = 0; i < beanList.getLength(); i++) {
                Node beanNode = beanList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");

                    NodeList beanChildNodeList = beanElement.getChildNodes();

                    for (int j = 0; j < beanChildNodeList.getLength(); j++) {
                        Node beanChildNode = beanChildNodeList.item(j);
                        if (beanChildNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())) {
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            // 1) 找到propertyRef对应的实例
                            Object refObj = beanMap.get(propertyRef);
                            // 2) 将refObj设置到当前bean对应的实例的property属性上去
                            Object beanObj = beanMap.get(beanId);
                            Field propertyField = beanObj.getClass().getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj, refObj);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String beanId) {
        return beanMap.get(beanId);
    }
}
