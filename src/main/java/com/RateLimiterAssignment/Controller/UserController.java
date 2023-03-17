package com.RateLimiterAssignment.Controller;

import com.RateLimiterAssignment.Request.UserDetailsRequestModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @PostMapping
    public String createUser(@RequestBody UserDetailsRequestModel userDetails){
        return "Hello";
        }
}
