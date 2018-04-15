package com.vpaveldm.command;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GoogleImplCommand implements ICommand {

    private String auth_uri;
    private String redirect_uri;
    private String client_id;

    public GoogleImplCommand() {
        File propertiesFile = new File("E:\\aipos2\\lab5\\Servlet\\src\\main\\resources\\properties.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(propertiesFile);
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder properties = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                properties.append(line);
            }
            JSONObject propertiesJson = new JSONObject(properties.toString()).getJSONObject("web");
            client_id = propertiesJson.getString("client_id");
            JSONArray redirectUrisArray = propertiesJson.getJSONArray("redirect_uris");
            redirect_uri = redirectUrisArray.getString(0);
            auth_uri = propertiesJson.getString("auth_uri");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return String.format("https://accounts.google.com/o/oauth2/auth?redirect_uri=%s&response_type=code&client_id=%s&scope=https://www.google.com/m8/feeds/", redirect_uri, client_id);
    }

    @Override
    public void handle(HttpServletRequest request) {
        String code = request.getParameter("code");
        System.out.println(code);
    }

    @Override
    public NextOperation getNextOperation() {
        return NextOperation.REDIRECT;
    }
}
