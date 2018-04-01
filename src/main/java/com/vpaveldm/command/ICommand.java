package com.vpaveldm.command;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    public String execute(HttpServletRequest request);
}
