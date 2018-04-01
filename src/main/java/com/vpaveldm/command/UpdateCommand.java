package com.vpaveldm.command;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

import static com.vpaveldm.servlet.ServletUrils.PASSWORD;
import static com.vpaveldm.servlet.ServletUrils.URL;
import static com.vpaveldm.servlet.ServletUrils.USER;

public class UpdateCommand implements ICommand {
    public UpdateCommand() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override

    public String execute(HttpServletRequest request) {
        return "/pages/update.jsp";
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
            Integer id = Integer.valueOf(request.getParameter("id"));
            String name = request.getParameter("name");
            Integer speed = Integer.valueOf(request.getParameter("speed"));
            String cable = request.getParameter("cable");
            String standard = request.getParameter("standard");
            statement.executeUpdate("UPDATE localnetwork SET " +
                    "name='" + name + "', " +
                    "speed='" + speed + "', " +
                    "cable='" + cable + "', " +
                    "standard='" + standard + "' " +
                    "WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
