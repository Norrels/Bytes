package com.bytes.bytes.contexts.kitchen.utils;

import com.bytes.bytes.contexts.kitchen.adapters.inbound.dtos.UserRequest;
import com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.entities.UserEntity;
import com.bytes.bytes.contexts.kitchen.domain.models.User;
import com.bytes.bytes.contexts.shared.dtos.UserDTO;
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

    public UserDTO userRequestToUserDTOMapper(UserRequest userRequest){
        UserDTO user = new UserDTO();
        BeanUtils.copyProperties(userRequest, user);
        return user;
    }

    public User userDTOToUserMapper(UserDTO useDto){
        User user = new User();
        BeanUtils.copyProperties(useDto, user);
        return user;
    }

    public UserDTO UserToUserDTOMapper(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
