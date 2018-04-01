package com.vpaveldm.servlet;

import com.vpaveldm.command.CommandFactory;
import com.vpaveldm.command.ICommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ControllerServlet")
public class Controller extends HttpServlet {

    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(COMMAND);
        ICommand command = new CommandFactory().defineCommand(action);
        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
