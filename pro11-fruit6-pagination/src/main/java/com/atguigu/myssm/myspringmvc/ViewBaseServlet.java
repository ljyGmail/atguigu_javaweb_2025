package com.atguigu.myssm.myspringmvc;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: ViewBaseServlet
 * Package: com.atguigu.fruit.myssm.myspringmvc
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 22. 오후 4:39
 * @Version 1.0
 */
public class ViewBaseServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        // 1. 获取ServletContext对象
        ServletContext servletContext = this.getServletContext();

        // 2. 创建Thymeleaf解析器对象
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);

        // 3. 给解析器对象设置参数
        // 1️⃣ HTML是默认模式，明确设置是为了代码更容易理解
        templateResolver.setTemplateMode(TemplateMode.HTML);

        // 2️⃣ 设置前缀
        String viewPrefix = servletContext.getInitParameter("view-prefix");
        templateResolver.setPrefix(viewPrefix);

        // 3️⃣ 设置后缀
        String viewSuffix = servletContext.getInitParameter("view-suffix");
        templateResolver.setSuffix(viewSuffix);

        // 4️⃣ 设置缓存过期时间
        templateResolver.setCacheTTLMs(60000L);

        // 5️⃣ 设置是否缓存
        templateResolver.setCacheable(true);

        // 6️⃣ 设置服务器端的编码方式
        templateResolver.setCharacterEncoding("UTF-8");

        // 4. 创建模板引擎对象
        templateEngine = new TemplateEngine();

        // 5. 给模板引擎对象设置模板解析器
        templateEngine.setTemplateResolver(templateResolver);
    }

    protected void processTemplate(String templateName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 设置响应体内容类型和字符集
        response.setContentType("text/html;charset=UTF-8");

        // 2. 创建WebContext对象
        WebContext webContext = new WebContext(request, response, getServletContext());

        // 3. 处理模板数据
        templateEngine.process(templateName, webContext, response.getWriter());
    }
}
