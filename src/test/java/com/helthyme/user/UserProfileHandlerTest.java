package com.helthyme.user;


import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.Context;
import com.helthyme.user.model.UserData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.fail;

public class UserProfileHandlerTest {

    private static UserProfileSpringStreamHandler handler;
    private static Context lambdaContext;

    @BeforeAll
    public static void setup() {
        lambdaContext = new MockLambdaContext();
        handler = new UserProfileSpringStreamHandler();
    }


    @Disabled
    @Test
    public void testApp() {
        try {
            UserData userData = UserData.builder()
                    .firstName("supimi")
                    .lastName("piumika")
                    .userName("supimip")
                    .gender("Female")
                    .build();
            InputStream inputStream = new AwsProxyRequestBuilder("/user", HttpMethod.POST)
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .body(userData)
                    .buildStream();
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            handle(inputStream, responseStream);
//            System.out.println(responseStream.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handle(InputStream is, ByteArrayOutputStream os) {
        try {
            handler.handleRequest(is, os, lambdaContext);
            System.out.println(os.toString());
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
