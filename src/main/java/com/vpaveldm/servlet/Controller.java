package com.vpaveldm.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ControllerServlet")
public class Controller extends Servlet {
    private static final String COMMAND = "command";

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action = req.getParameter(COMMAND);
        super.handleRequest(req, resp);
    }
}
