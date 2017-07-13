package com.gs.apicall.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by govinda.v on 13/07/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiConfig {
    private String serviceId;

    private String apiName;
    private String api;
    private int priority;
}
