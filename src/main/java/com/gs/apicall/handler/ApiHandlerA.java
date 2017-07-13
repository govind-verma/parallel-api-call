package com.gs.apicall.handler;

import com.gs.apicall.pojo.ApiConfig;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by govinda.v on 13/07/17.
 */
@Component
public class ApiHandlerA implements IApiHandler {

    @Override
    public String getApiName() {
        return "API_A";
    }

    @Override
    public Void handle(ApiConfig apiConfig, Map<String, Object> request, Map<String, Object> response) {
        /**
         * Your logic here
         */
        System.out.println("Handling Api A" + Thread.currentThread().getName());
        response.put("ResponseFromA", "ResponseFromA");

        return null;
    }
}
