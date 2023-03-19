package com.RateLimiterAssignment.Sevice;

import com.RateLimiterAssignment.Entity.UserEntity;
import com.RateLimiterAssignment.Repository.UserRepository;
import com.RateLimiterAssignment.Shared.UserDto;
import com.RateLimiterAssignment.Shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    Utils utils;


    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null)
            throw  new RuntimeException("Record already exception");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserID = utils.generateUserId(30);
        userEntity.setUserId(publicUserID);

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails,returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUserByEmail(String email)  {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findByEmail(email);
        System.out.println(userEntity.toString());
        if (userEntity == null) throw new NullPointerException();
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public void deleteUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        userRepository.delete(userEntity);
    }


}





