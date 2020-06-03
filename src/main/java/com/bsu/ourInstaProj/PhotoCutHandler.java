package com.bsu.ourInstaProj;

import com.bsu.ourInstaProj.entity.request.PhotoRequest;
import com.bsu.ourInstaProj.entity.response.PhotoVO;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import java.util.Optional;

public class PhotoCutHandler extends AzureSpringBootRequestHandler<PhotoRequest, PhotoVO> {

    @FunctionName("photoCut")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<PhotoRequest>> request,
            ExecutionContext context) {

        context.getLogger().info("Id photo to cut: " + request.getBody().get().getUrl());
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(request.getBody().get(), context))
                .header("Content-Type", "application/json")
                .build();
    }
}