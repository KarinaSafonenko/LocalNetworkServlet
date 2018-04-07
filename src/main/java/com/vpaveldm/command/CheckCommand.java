package com.vpaveldm.command;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.vpaveldm.entity.LocalNetwork;
import com.vpaveldm.entity.Standard;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.vpaveldm.servlet.ServletUrils.PASSWORD;
import static com.vpaveldm.servlet.ServletUrils.URL;
import static com.vpaveldm.servlet.ServletUrils.USER;

public class CheckCommand implements ICommand {

    private static final String LOCAL_NETWORK_SET = "localNetworkSet";

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
                String standardName = resultSet.getString("standard_name");
                String standardLand = resultSet.getString("standard_land");
                Standard standard = new Standard(standardName, standardLand);
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

    @Override
    public void handle(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }
}
