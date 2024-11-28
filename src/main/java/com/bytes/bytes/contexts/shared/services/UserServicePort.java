package com.bytes.bytes.contexts.shared.services;

import com.bytes.bytes.contexts.kitchen.core.domain.models.User;
import com.bytes.bytes.contexts.shared.dtos.UserDTO;

public interface UserServicePort {
    UserDTO createUser(UserDTO user);
    UserDTO update(Long id, UserDTO user);
    UserDTO delete(Long id);
    String autenticate(String email, String password);
}
