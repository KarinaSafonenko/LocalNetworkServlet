package com.vpaveldm.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
    String execute(HttpServletRequest request, HttpServletResponse response);
    void handle(HttpServletRequest request);
    NextOperation getNextOperation();
}
