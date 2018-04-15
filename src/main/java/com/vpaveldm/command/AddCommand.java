package com.vpaveldm.command;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import static com.vpaveldm.servlet.ServletUrils.*;

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
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
            if (name.equals(""))
                return;
            String speed = request.getParameter("speed");
            if (speed.equals(""))
                return;
            String cable = request.getParameter("cable");
            if (cable.equals(""))
                return;
            String standardName = request.getParameter("standard_name");
            if (standardName.equals(""))
                return;
            String standardLand = request.getParameter("standard_land");
            if (standardLand.equals(""))
                return;
            statement.execute("INSERT INTO localnetwork (name, speed, cable, standard_name, standard_land) VALUES ('"
                    + name + "', '" + speed + "', '" + cable + "', '" + standardName + "', '" + standardLand + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public NextOperation getNextOperation() {
        return NextOperation.FORWARD;
    }
}
