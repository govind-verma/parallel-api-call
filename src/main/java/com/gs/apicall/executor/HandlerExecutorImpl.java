package com.gs.apicall.executor;

import com.gs.apicall.handler.IApiHandler;
import com.gs.apicall.pojo.ApiConfig;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by govinda.v on 13/07/17.
 */
@Component
public class HandlerExecutorImpl implements IHandlerExecutor {

    @Override
    public Future<Void> handle(IApiHandler iApiHandler, ApiConfig apiConfig,
                               Map<String, Object> request, Map<String, Object> response) {

        return new AsyncResult<>(iApiHandler.handle(apiConfig, request, response));
    }
}
