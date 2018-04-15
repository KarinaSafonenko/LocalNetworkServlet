package com.vpaveldm.command;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import static com.vpaveldm.servlet.ServletUrils.*;

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

    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
            String idParameter = request.getParameter("id");
            if (idParameter.equals(""))
                return;
            Integer id = Integer.valueOf(idParameter);
            String name = request.getParameter("name");
            String speed = request.getParameter("speed");
            String cable = request.getParameter("cable");
            String standardName = request.getParameter("standard_name");
            String standardLand = request.getParameter("standard_land");
            String fields = "";
            fields = add(fields, String.valueOf(id), "id");
            fields = add(fields, name, "name");
            fields = add(fields, speed, "speed");
            fields = add(fields, cable, "cable");
            fields = add(fields, standardName, "standard_name");
            fields = add(fields, standardLand, "standard_land");
            fields = fields.substring(0, fields.length() - 2) + " ";
            statement.executeUpdate("UPDATE localnetwork SET " +
                    fields + "WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public NextOperation getNextOperation() {
        return NextOperation.FORWARD;
    }

    private String add(String fields, String field, String fieldName) {
        if (!field.equals(""))
            fields = fields + fieldName + "='" + field + "', ";
        return fields;
    }
}
