package com.vpaveldm.command;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

import static com.vpaveldm.servlet.ServletUrils.PASSWORD;
import static com.vpaveldm.servlet.ServletUrils.URL;
import static com.vpaveldm.servlet.ServletUrils.USER;

public class AddCommand implements ICommand {
    public AddCommand() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String execute(HttpServletRequest request) {
        return "/pages/add.jsp";
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
            String name = request.getParameter("name");
            Integer speed = Integer.valueOf(request.getParameter("speed"));
            String cable = request.getParameter("cable");
            String standard = request.getParameter("standard");
            statement.execute("INSERT INTO localnetwork (name, speed, cable, standard) VALUES ('"
                    + name + "', '" + speed + "', '" + cable + "', '" + standard + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
