package cn.yueshutong.springprojecttree.controller;

import cn.yueshutong.springprojecttree.config.ApplicationContextHelper;
import cn.yueshutong.springprojecttree.db.entity.MethodNode;
import cn.yueshutong.springprojecttree.db.service.MethodNodeService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Create by yster@foxmail.com 2019/1/31 0031 19:51
 */
@WebServlet(name = "TreeServlet", urlPatterns = "/projecttree/*", loadOnStartup = 1)
public class TreeServlet extends HttpServlet {

    private MethodNodeService methodNodeService = ApplicationContextHelper.popBean(MethodNodeService.class);

    private Logger logger = LoggerFactory.getLogger(TreeServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/json; charset=utf-8");
        String str = JSON.toJSONString(getViewAll());
        PrintWriter out = resp.getWriter();
        out = resp.getWriter();
        out.println(str);
    }
    /**
     * 返回HTML网页形式的分析结果
     * @return
     */
    public List<MethodNode> getViewAll(){
        List<MethodNode> nodes = methodNodeService.findAll();
        if (nodes!=null) {
            nodes = nodes.stream().filter(MethodNode::isFirst).collect(Collectors.toList());
        }
        return nodes;
    }

    public List<MethodNode> getView(){
        return methodNodeService.findAll();
    }

    public MethodNode getAll(Long methodId){
        return methodNodeService.findAllById(methodId);
    }


}
