package com.RateLimiterAssignment.Sevice;

import com.RateLimiterAssignment.Shared.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService  {
    UserDto createUser(UserDto user);

   // UserDto getUserByUserId(String userId);

    UserDto getUserByEmail(String email);

    void deleteUser(String userId);


}
