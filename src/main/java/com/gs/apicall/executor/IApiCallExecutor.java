package com.gs.apicall.executor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by govinda.v on 13/07/17.
 */
public interface IApiCallExecutor {

    Map<String, Object> execute(String apiId, Map<String, Object> request);
}
