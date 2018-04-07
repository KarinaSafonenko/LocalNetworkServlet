package com.vpaveldm.command;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.vpaveldm.servlet.ServletUrils.PASSWORD;
import static com.vpaveldm.servlet.ServletUrils.URL;
import static com.vpaveldm.servlet.ServletUrils.USER;

public class DeleteCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "/pages/delete.jsp";
    }

    @Override
    public void handle(HttpServletRequest request) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String id = request.getParameter("id");
            if (id.equals(""))
                return;
            statement.executeUpdate("DELETE FROM localnetwork WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
