package com.RateLimiterAssignment.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message: ", message);
        map.put("status: ", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map,status);
    }
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message: ", message);
        map.put("status: ", status.value());

        return new ResponseEntity<Object>(map,status);
    }
}
//    Token Bucket is an algorithm that you can use to implement rate limiting. In short, it works as follows:
//
//        A bucket is created with a certain capacity (number of tokens).
//        When a request comes in, the bucket is checked. If there is enough capacity, the request is allowed to proceed. Otherwise, the request is denied.
//        When a request is allowed, the capacity is reduced.
//        After a certain amount of time, the capacity is replenished.