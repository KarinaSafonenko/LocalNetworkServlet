package com.vpaveldm.command;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class VKCommand implements ICommand {

    private String client_id;
    private String redirect_uri;
    private String scope;
    private String version;
    private String client_secret;

    public VKCommand() {
        File propertiesFile = new File("E:\\aipos2\\lab5\\Servlet\\src\\main\\resources\\properties.json");
        FileReader fileReader;
        try {
            fileReader = new FileReader(propertiesFile);
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder properties = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                properties.append(line);
            }
            JSONObject propertiesJson = new JSONObject(properties.toString()).getJSONObject("vk");
            client_id = propertiesJson.getString("client_id");
            JSONArray redirectUrisArray = propertiesJson.getJSONArray("redirect_uris");
            redirect_uri = redirectUrisArray.getString(0);
            version = propertiesJson.getString("version");
            scope = propertiesJson.getString("scope");
            client_secret = propertiesJson.getString("client_secret");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return String.format("https://oauth.vk.com/authorize?client_id=%s&display=page&redirect_uri=%s&scope=%s&response_type=code&v=%s",
                client_id, redirect_uri, scope, version);
    }

    @Override
    public void handle(HttpServletRequest request) {
        String code = request.getParameter("code");
        HttpClient client = new HttpClient();
        PostMethod getAccessTokenMethod = new PostMethod("https://oauth.vk.com/access_token");
        getAccessTokenMethod.addParameter("client_id", client_id);
        getAccessTokenMethod.addParameter("client_secret", client_secret);
        getAccessTokenMethod.addParameter("redirect_uri", redirect_uri);
        getAccessTokenMethod.addParameter("code", code);
        try {
            client.executeMethod(getAccessTokenMethod);
            InputStreamReader reader = new InputStreamReader(getAccessTokenMethod.getResponseBodyAsStream());
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject userResponse = new JSONObject(tokener);
            int user_id = userResponse.getInt("user_id");
            reader.close();
            PostMethod getInfoMethod = new PostMethod("https://api.vk.com/method/users.get");
            getInfoMethod.addParameter("user_ids", String.valueOf(user_id));
            getInfoMethod.addParameter("v", version);
            client.executeMethod(getInfoMethod);
            reader = new InputStreamReader(getInfoMethod.getResponseBodyAsStream(), "UTF-8");
            tokener = new JSONTokener(reader);
            JSONObject userInfoResponse = new JSONObject(tokener).getJSONArray("response").getJSONObject(0);
            String firstName = userInfoResponse.getString("first_name");
            String lastName = userInfoResponse.getString("last_name");
            request.setAttribute("name", firstName + " " + lastName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public NextOperation getNextOperation() {
        return NextOperation.REDIRECT;
    }
}
