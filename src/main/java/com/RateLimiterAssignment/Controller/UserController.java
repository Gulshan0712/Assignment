package com.RateLimiterAssignment.Controller;

import com.RateLimiterAssignment.Request.UserDetailsRequestModel;
import com.RateLimiterAssignment.Response.ResponseHandler;
import com.RateLimiterAssignment.Response.UserRest;
import com.RateLimiterAssignment.Sevice.UserService;
import com.RateLimiterAssignment.Shared.UserDto;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/users")
public class UserController {
    private Bucket createUserBucket;
    private Bucket getUserBucket;
    @Autowired
    UserService userSevice;
    @GetMapping("/token-generate")
    public ResponseEntity<String> generateToken() {
       createUserBucket =  Bucket4j.builder()
              .addLimit(Bandwidth.classic(3,Refill.intervally(3,Duration.ofSeconds(15))))
              .build();
       getUserBucket = Bucket4j.builder()
               .addLimit(Bandwidth.classic(3,Refill.intervally(3,Duration.ofSeconds(10))))
               .build();
        return new ResponseEntity<String>("Generated Successfully", HttpStatus.OK);

    }

    @GetMapping("/demo")
    public ResponseEntity<Object> createUser(@RequestBody UserDetailsRequestModel userDetails){


        if (createUserBucket.tryConsume(1)){
            UserRest result = new UserRest();
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userDetails, userDto);
            UserDto createdUser = userSevice.createUser(userDto);
            BeanUtils.copyProperties(createdUser, result);
            System.out.println("API working succesfully");
            return new ResponseHandler().generateResponse("User created Successfully",HttpStatus.OK,result);
        }
        System.out.println("Number of hits exceeded");
        return  new ResponseHandler().generateResponse("Hits exhausted",HttpStatus.TOO_MANY_REQUESTS);
    }



    @GetMapping("/{email}")
    public ResponseEntity<Object>getUser(@PathVariable String email) {
        if (getUserBucket.tryConsume(1)) {
            UserRest returnValue = new UserRest();
            UserDto userDto = userSevice.getUserByEmail(email);
            BeanUtils.copyProperties(userDto, returnValue);
            return new ResponseHandler().generateResponse("User created Successfully",HttpStatus.OK,returnValue);
        }
        return new ResponseHandler().generateResponse("No. of hits exceeded",HttpStatus.TOO_MANY_REQUESTS);
    }
        @DeleteMapping(path = "/{email}")
        public String deleteUserById(@PathVariable String email){
        userSevice.deleteUser(email);
        return "User deleted successfully";
        }
}
