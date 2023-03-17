package com.RateLimiterAssignment.Controller;

import com.RateLimiterAssignment.Request.UserDetailsRequestModel;
import com.RateLimiterAssignment.Response.UserRest;
import com.RateLimiterAssignment.sevice.UserSevice;
import com.RateLimiterAssignment.shared.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserSevice userSevice;
    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails){

        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails,userDto);

        UserDto createdUser = userSevice.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return  returnValue;

        }
}
