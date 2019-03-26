package com.gateway;

import com.gateway.model.Request;
import com.gateway.model.Response;
import com.gateway.model.request.Authorization;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import static org.apache.http.protocol.HTTP.USER_AGENT;


public class Gateway {

    private final Gson gson;
    private String url;
    private Authorization authorization;
    private HttpClient httpClient;

    public Gateway(String url, Authorization authorization, HttpClient httpClient) {
        this.url = url;
        this.authorization = authorization;
        this.httpClient = httpClient;
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .create();
    }

    public void request(Request rr) throws IOException {
        rr.setAuthorization(authorization);
        String postBody = gson.toJson(rr);
        HttpPost request = new HttpPost(url + "/sms");
        request.addHeader("User-Agent", USER_AGENT);
        request.setEntity(new StringEntity(postBody));


        HttpResponse response = httpClient.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        String body = EntityUtils.toString(response.getEntity());
        //System.out.println("Response Body : " + body);

        Response response1 = gson.fromJson(body, Response.class);

        System.out.println("Response Body : " + body);
//        System.out.println("Response Body : "+ body);
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }

//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .build();
//        HttpResponse<String> response = null;
//
//        try {
//            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(response.statusCode());
//        System.out.println(response.body());
    }
}
