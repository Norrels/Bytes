package com.bytes.bytes.contexts.kitchen.utils;

import com.bytes.bytes.contexts.kitchen.adapters.inbound.dtos.UserRequest;
import com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.entities.UserEntity;
import com.bytes.bytes.contexts.kitchen.core.domain.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity userToUserEntityMapper(User user){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        return userEntity;
    }

    public User userEntityToUserMapper(UserEntity userEntity){
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    public User userRequestToUserEntityMapper(UserRequest userRequest){
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        return user;
    }
}
