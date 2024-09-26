package com.example.ordersyncscheduler.util;


import com.example.ordersyncscheduler.dto.SaleOrdersDTO;
import com.example.ordersyncscheduler.dto.TokenResponse;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestClient {
    @Value("${app.base-url}")
    private static String baseUrl;

    @Value("${app.username}")
    private static String username;

    @Value("${app.password}")
    private static String password;
    private String token;
    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    public void signIn() throws IOException {
        String signInJson = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);
        HttpResponse response = Request.Post(baseUrl + "sign-in")
                .bodyString(signInJson, ContentType.APPLICATION_JSON)
                .execute().returnResponse();

        checkResponse(response);
        String responseContent = EntityUtils.toString(response.getEntity());
        token = parseToken(responseContent);  // 解析响应以获取token

    }

    public void sendOrder(SaleOrdersDTO order) throws Exception {
        String orderJson = JsonUtil.convertToJson(order);  // 将SaleOrder对象转换为JSON格式
        HttpResponse response = Request.Post(baseUrl + "inbound/create")
                .addHeader("Authorization", "Bearer " + token)
                .bodyString(orderJson, ContentType.APPLICATION_JSON)
                .execute().returnResponse();

        checkResponse(response);
    }

    private void checkResponse(HttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        switch (statusCode) {
            case HttpStatus.SC_OK, HttpStatus.SC_CREATED, HttpStatus.SC_NO_CONTENT ->
                    logger.info("Request succeeded with status code {}", statusCode);
            default -> {
                String responseString = EntityUtils.toString(response.getEntity());
                logger.error("Request failed with status code {}: {}", statusCode, responseString);
                throw new HttpResponseException(
                        statusCode,
                        "Received error response: " + responseString
                );
            }
        }
    }

    public static String parseToken(String jsonResponse) {
        TokenResponse tokenResponse = JsonUtil.convertFromJson(jsonResponse, TokenResponse.class);
        return tokenResponse != null ? tokenResponse.getToken() : null;
    }

}
