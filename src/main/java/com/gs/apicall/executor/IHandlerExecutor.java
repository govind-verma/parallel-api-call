package com.gs.apicall.executor;

import com.gs.apicall.handler.IApiHandler;
import com.gs.apicall.pojo.ApiConfig;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by govinda.v on 13/07/17.
 */
public interface IHandlerExecutor {

    @Async
    Future<Void> handle(IApiHandler iApiHandler, ApiConfig apiConfig,
                        Map<String, Object> request, Map<String, Object> response);
}
