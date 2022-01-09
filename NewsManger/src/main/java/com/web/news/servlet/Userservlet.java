package com.web.news.servlet;

import com.get.jdbc.dao.UserDao;
import com.get.jdbc.entity.Users;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Userservlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8"); // 添加 todo:

        String action = req.getParameter("action");
        // 新闻列表
        if ("login".equals(action)) {
            doLogin(req, resp);
        }
        else if ("logout".equals(action)) {
            doLogout(req, resp);
        }
    }

    private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // logout 退出的话销毁session
        HttpSession session = req.getSession();
        session.invalidate();
        // 跳回首页
        resp.sendRedirect(req.getContextPath()+ "/NewsServlet?action=listNews");
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username  = req.getParameter("uname");
        String userpwd = req.getParameter("upwd");
        UserDao userDao = new UserDao();
        Users loginUser = userDao.login(username,userpwd);
        if(loginUser != null)
        {
            HttpSession session = req.getSession();
            session.setAttribute("admin",loginUser);
            resp.sendRedirect(req.getContextPath()+ "/NewsServlet?action=listNews");
        }
        else{
            // 失败调整
        }

    }
}
