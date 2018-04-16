package com.vpaveldm.servlet;

import com.vpaveldm.command.CommandFactory;
import com.vpaveldm.command.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Servlet extends HttpServlet{
    String action;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();
        ICommand command = commandFactory.defineCommand(action);
        if (commandFactory.isHandleCommand(action)) {
            command.handle(req);
            req.getRequestDispatcher("pages/workspace.jsp").forward(req, resp);
        } else {
            String page = command.execute(req, resp);
            switch (command.getNextOperation()) {
                case FORWARD: {
                    req.getRequestDispatcher(page).forward(req, resp);
                    break;
                }
                case REDIRECT: {
                    resp.sendRedirect(page);
                    break;
                }
            }
        }
    }
}
