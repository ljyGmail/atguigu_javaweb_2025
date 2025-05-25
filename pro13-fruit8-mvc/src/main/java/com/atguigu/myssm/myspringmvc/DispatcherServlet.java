package com.atguigu.myssm.myspringmvc;

import com.atguigu.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: DispatcherServlet
 * Package: com.atguigu.myssm.myspringmvc
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 25. 오후 12:11
 * @Version 1.0
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private Map<String, Object> beanMap = new HashMap<>();

    public DispatcherServlet() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
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
                    Object beanObj = Class.forName(className).newInstance();
                    beanMap.put(beanId, beanObj);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置编码
        request.setCharacterEncoding("UTF-8");

        // 假设url是: http://localhost:8080/pro13/hello.do
        // 那么servletPath是: /hello.do
        // 思路: 第1步: /hello.do -> hello
        // 第2步: hello -> HelloController
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDotIndex);

        Object controllerBeanObj = beanMap.get(servletPath);

        String operate = request.getParameter("operate");

        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        try {
            Method method = controllerBeanObj.getClass().getDeclaredMethod(operate, HttpServletRequest.class);
            if (method != null) {
                // 2. Controller组件中的方法调用
                method.setAccessible(true);
                Object returnObj = method.invoke(controllerBeanObj, request);

                // 3. 视图处理
                String methodReturnStr = (String) returnObj;
                if (methodReturnStr.startsWith("redirect:")) { // 例如: redirect:fruit.do
                    String redirectStr = methodReturnStr.substring("redirect:".length());
                    response.sendRedirect(redirectStr);
                } else {
                    processTemplate(methodReturnStr, request, response); // 例如: "edit"
                }
            } else {
                throw new RuntimeException("operate值非法!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
