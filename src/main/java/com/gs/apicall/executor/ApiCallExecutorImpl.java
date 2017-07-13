package com.gs.apicall.executor;

import com.gs.apicall.handler.IApiHandler;
import com.gs.apicall.pojo.ApiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * Created by govinda.v on 13/07/17.
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiCallExecutorImpl implements IApiCallExecutor {

    private Map<String, List<ApiConfig>> apiNameToConfig;

    private Map<String, IApiHandler> apiHandlerMap;

    private final ApplicationContext applicationContext;

    private final IHandlerExecutor iHandlerExecutor;

    @PostConstruct
    private void init() {
        apiNameToConfig = new HashMap<>();
        apiHandlerMap = new HashMap<>();

        List<ApiConfig> apiConfigs = new ArrayList<>();

        apiConfigs.add(buildApiConfig("ID-1", "A", "API_A", 1));
        apiConfigs.add(buildApiConfig("ID-1", "B", "API_B", 2));
        apiConfigs.add(buildApiConfig("ID-1", "C", "API_C", 2));
        apiConfigs.add(buildApiConfig("ID-1", "D", "API_D", 3));

        apiNameToConfig.put("ID-1", apiConfigs);

        Map<String, IApiHandler> apiHandlerMap = applicationContext.getBeansOfType(IApiHandler.class);

        apiHandlerMap.forEach((key, value) -> {
            this.apiHandlerMap.put(value.getApiName(), value);
        });
    }

    /**
     * YOu can also get List<ApiConfig> from caller of below method
     *
     * @param serviceId
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> execute(String serviceId, Map<String, Object> request) {
        List<ApiConfig> apiConfigs = apiNameToConfig.get(serviceId);

        Collections.sort(apiConfigs, buildApiConfigComparator());

        Map<String, Object> response = new ConcurrentHashMap<>();

        int size = apiConfigs.size();
        for (int i = 0; i < size; ) {
            int j = findIndexOfSamePriority(i, size, apiConfigs);

            if (i == j) {

                ApiConfig apiConfig = apiConfigs.get(i);
                IApiHandler apiHandler = apiHandlerMap.get(apiConfig.getApiName());

                iHandlerExecutor.handle(apiHandler, apiConfig, request, response);
                System.out.println("ApiCallExecutorImpl" + Thread.currentThread().getName());
                i++;
            } else {

                int k = i;
                List<Future<Void>> futures = new ArrayList<>();

                while (k < j) {

                    ApiConfig apiConfig = apiConfigs.get(k);
                    IApiHandler apiHandler = apiHandlerMap.get(apiConfig.getApiName());

                    futures.add(iHandlerExecutor.handle(apiHandler, apiConfig, request, response));

                    k++;
                }
                i = j;
                /**
                 * We can break here with some logic or timeout
                 */
                while (true) {
                    int doneTask = 0;

                    for (Future future : futures) {
                        if (future.isDone()) {
                            doneTask++;
                        }
                    }

                    if (doneTask == futures.size()) {
                        break;
                    }
                }
            }
        }
        return response;
    }

    private int findIndexOfSamePriority(int start, int end, List<ApiConfig> apiConfigs) {

        int prevPriority = apiConfigs.get(start).getPriority();

        for (start = start + 1; start < end; start++) {
            if (prevPriority != apiConfigs.get(start).getPriority()) {
                return start;
            }
        }

        return start;
    }

    private ApiConfig buildApiConfig(String serviceId, String api, String apiName, int priority) {
        return ApiConfig.builder()
                .serviceId(serviceId)
                .api(api)
                .apiName(apiName)
                .priority(priority)
                .build();
    }

    private Comparator<ApiConfig> buildApiConfigComparator() {
        return new Comparator<ApiConfig>() {
            @Override
            public int compare(ApiConfig o1, ApiConfig o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }

                if (o1 != null && o2 != null) {
                    if (o1.getPriority() == o2.getPriority()) {
                        return 0;
                    } else if (o1.getPriority() > o2.getPriority()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (o1 == null) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
    }
}
