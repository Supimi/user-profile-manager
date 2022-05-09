package com.helthyme.user;


import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.InitializationWrapper;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class UserProfileSpringStreamHandler implements RequestStreamHandler {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    public UserProfileSpringStreamHandler() {
        this.init();
    }

    public void init(){
        try {
            handler = new SpringBootProxyHandlerBuilder<AwsProxyRequest>()
                    .asyncInit()
                    .defaultProxy()
                    .initializationWrapper(new InitializationWrapper())
                    .springBootApplication(SprintBootApplication.class)
                    .buildAndInitialize();
        } catch (ContainerInitializationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        log.info("=====================App is starting===========================");
        handler.proxyStream(inputStream, outputStream, context);
    }
}
