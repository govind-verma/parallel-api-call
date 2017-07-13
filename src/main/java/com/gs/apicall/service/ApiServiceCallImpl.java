package com.gs.apicall.service;

import com.gs.apicall.executor.IApiCallExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by govinda.v on 13/07/17.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiServiceCallImpl implements IApiServiceCall {

    public static final String API_ID = "ID-1";

    private final IApiCallExecutor iApiCallExecutor;

    /**
     * This service know how many service call from this service. Let assign an id;ß
     *
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> call(Map<String, Object> request) {

        return iApiCallExecutor.execute(API_ID, request);
    }
}
