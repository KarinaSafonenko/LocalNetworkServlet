package com.vpaveldm.command;

import javax.servlet.http.HttpServletRequest;

public class ChangeCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "/pages/change.jsp";
    }
}
