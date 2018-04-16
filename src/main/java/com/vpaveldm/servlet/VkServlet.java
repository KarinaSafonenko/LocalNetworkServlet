package com.vpaveldm.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/VkServlet")
public class VkServlet extends Servlet {
    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action = "VK_FINISHED";
        super.handleRequest(req, resp);
    }
}
