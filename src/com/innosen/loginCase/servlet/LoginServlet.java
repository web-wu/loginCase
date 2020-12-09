package com.innosen.loginCase.servlet;

import com.innosen.loginCase.dao.UserDao;
import com.innosen.loginCase.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginuser = new User();
        loginuser.setUsername(username);
        loginuser.setPassword(password);
        UserDao userDao = new UserDao();
        User user = userDao.login(loginuser);
        if (user == null) {
            req.getRequestDispatcher("/loginFailed").forward(req,resp);
        } else {
            req.setAttribute("User", user);
            req.getRequestDispatcher("/loginSuccess").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
