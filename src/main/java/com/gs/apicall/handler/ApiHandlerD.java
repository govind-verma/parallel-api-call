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
public class ApiHandlerD implements IApiHandler {

    @Override
    public String getApiName() {
        return "API_D";
    }

    @Override
    public Void handle(ApiConfig apiConfig, Map<String, Object> request, Map<String, Object> response) {
        /**
         * Your logic here
         */

        System.out.println("Handling Api D" + Thread.currentThread().getName());
        response.put("ResponseFromD", "ResponseFromD");

        System.out.println(response.get("ResponseFromA") + Thread.currentThread().getName());
        System.out.println(response.get("ResponseFromB") + Thread.currentThread().getName());
        System.out.println(response.get("ResponseFromC") + Thread.currentThread().getName());

        return null;
    }
}