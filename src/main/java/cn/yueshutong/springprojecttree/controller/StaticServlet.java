package cn.yueshutong.springprojecttree.controller;

import cn.yueshutong.springprojecttree.util.ReadClasspathFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "StaticServlet", urlPatterns = "/tree/*", loadOnStartup = 1)
public class StaticServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(StaticServlet.class);


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        view_js(resp, req);
    }

    /**
     * 解决静态资源不加载
     */
    public void view_js(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String uri = request.getRequestURI().trim();
        int toIndex = uri.indexOf("/tree");
        uri = uri.substring(toIndex,uri.length());
        logger.debug("uri = " + uri);
        if (uri.endsWith(".js")){
            response.setContentType("application/javascript");
        }else if (uri.endsWith(".css")){
            response.setContentType("text/css");
        }else if (uri.endsWith(".woff")){
            response.setContentType("application/x-font-woff");
        }else if (uri.endsWith(".ttf")){
            response.setContentType("application/x-font-truetype");
        }else if (uri.endsWith(".html")){
            response.setContentType("text/html");
        }
        byte[] s = ReadClasspathFile.read(uri);
        response.getOutputStream().write(Optional.ofNullable(s).orElse("404".getBytes()));
    }


}
