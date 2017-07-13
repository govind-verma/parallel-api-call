package com.gs.apicall.controller;

import com.gs.apicall.service.IApiServiceCall;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by govinda.v on 13/07/17.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiCallController {

    private final IApiServiceCall iApiServiceCall;

    @GetMapping("/call")
    ResponseEntity<?> call() {
        return new ResponseEntity<>(iApiServiceCall.call(null), HttpStatus.OK);
    }
}
