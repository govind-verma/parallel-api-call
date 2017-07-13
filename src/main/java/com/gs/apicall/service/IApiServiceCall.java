package com.gs.apicall.service;

import java.util.Map;

/**
 * Created by govinda.v on 13/07/17.
 */
public interface IApiServiceCall {
   Map<String, Object> call(Map<String, Object> request);
}
