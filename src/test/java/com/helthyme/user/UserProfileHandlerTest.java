package com.helthyme.user;


import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.Context;
import com.helthyme.user.model.ActivityData;
import com.helthyme.user.model.NutritionData;
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
    public void testSaveUser() {
        try {
            UserData userData = UserData.builder()
                    .firstName("supimi")
                    .lastName("piumika")
                    .username("supimip")
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

    @Disabled
    @Test
    public void testGetUser() {
        try {
            InputStream inputStream = new AwsProxyRequestBuilder("/user/466ce154-6e88-4dec-b901-1d7dfdd95ec1", HttpMethod.GET)
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .buildStream();
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            handle(inputStream, responseStream);
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

    @Disabled
    @Test
    public void testSaveUserActivity() {
        try {
            ActivityData activityData = ActivityData.builder()
                    .id( "123456" )
                    .activityId("co_01")
                    .activityName( "walking" )
                    .date( "2021-02-12" )
                    .userId( "1234" )
                    .duration( "12:12" )
                    .intensityLevel( "1" )
                    .build();
            InputStream inputStream = new AwsProxyRequestBuilder("/userActivity", HttpMethod.POST)
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .body(activityData)
                    .buildStream();
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            handle(inputStream, responseStream);
            //System.out.println(responseStream.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveUserNutrition() {
        try {
            NutritionData nutritionData = NutritionData.builder()
                    .id( "123456" )
                    .date( "2021-02-12" )
                    .userId( "1234" )
                    .food( "cup of tea" )
                    .build();
            InputStream inputStream = new AwsProxyRequestBuilder("/userNutrition", HttpMethod.POST)
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .body(nutritionData)
                    .buildStream();
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            handle(inputStream, responseStream);
            //System.out.println(responseStream.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
