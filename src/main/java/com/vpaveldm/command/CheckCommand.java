package com.vpaveldm.command;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.vpaveldm.entity.LocalNetwork;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckCommand implements ICommand {

    private static final String LOCAL_NETWORK_SET = "localNetworkSet";
    private static final String URL = "jdbc:mysql://localhost:3306/localnetworkdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public CheckCommand() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<LocalNetwork> networks = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM localnetwork");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Integer speed = resultSet.getInt("speed");
                String cable = resultSet.getString("cable");
                String standard = resultSet.getString("standard");
                Integer id = resultSet.getInt("id");
                LocalNetwork network = new LocalNetwork(id, name, cable, speed, standard);
                networks.add(network);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute(LOCAL_NETWORK_SET, networks);
        return "/pages/check.jsp";
    }
}