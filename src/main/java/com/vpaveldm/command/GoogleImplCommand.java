package com.vpaveldm.command;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class GoogleImplCommand implements ICommand {

    //    private static final String scope = "https://www.google.com/m8/feeds/";
    private static final String scope = "https://www.googleapis.com/auth/userinfo.profile";
    private String client_secret;
    private String redirect_uri_google_finished;
    private String client_id;
    private String token_uri;

    public GoogleImplCommand() {
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
            JSONObject propertiesJson = new JSONObject(properties.toString()).getJSONObject("google");
            client_id = propertiesJson.getString("client_id");
            client_secret = propertiesJson.getString("client_secret");
            JSONArray redirectUrisArray = propertiesJson.getJSONArray("redirect_uris");
            redirect_uri_google_finished = redirectUrisArray.getString(0);
            token_uri = propertiesJson.getString("token_uri");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return String.format("https://accounts.google.com/o/oauth2/auth?redirect_uri=%s&response_type=code&client_id=%s&scope=%s", redirect_uri_google_finished, client_id, scope);
    }

    @Override
    public void handle(HttpServletRequest request) {
        String code = request.getParameter("code");
        try {
            HttpClient client = new HttpClient();
            PostMethod getTokenMethod = new PostMethod(token_uri);
            getTokenMethod.addParameter("code", code);
            getTokenMethod.addParameter("client_id", client_id);
            getTokenMethod.addParameter("client_secret", client_secret);
            getTokenMethod.addParameter("redirect_uri", redirect_uri_google_finished);
            getTokenMethod.addParameter("grant_type", "authorization_code");
            getTokenMethod.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            client.executeMethod(getTokenMethod);
            JSONObject authResponse = new JSONObject(new JSONTokener(new InputStreamReader(getTokenMethod.getResponseBodyAsStream())));
            String accessToken = authResponse.getString("access_token");
            GetMethod getInfoMethod = new GetMethod("https://www.googleapis.com/oauth2/v1/userinfo?access_token="
                    + accessToken + "&alt=json");
//            getInfoMethod.addParameter("access_token", accessToken);
            new HttpClient().executeMethod(getInfoMethod);
            JSONTokener x = new JSONTokener(new InputStreamReader(getInfoMethod.getResponseBodyAsStream(), "UTF-8"));
            JSONObject userInfoJson = new JSONObject(x);
            String name = userInfoJson.getString("name");
            request.setAttribute("name", name);
            System.out.println(accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public NextOperation getNextOperation() {
        return NextOperation.REDIRECT;
    }
}
