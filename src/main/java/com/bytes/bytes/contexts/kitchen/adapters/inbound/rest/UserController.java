package com.bytes.bytes.contexts.kitchen.adapters.inbound.rest;

import com.bytes.bytes.contexts.kitchen.adapters.inbound.dtos.UserRequest;
import com.bytes.bytes.contexts.kitchen.utils.UserMapper;
import com.bytes.bytes.contexts.kitchen.core.application.services.UserService;
import com.bytes.bytes.contexts.kitchen.core.domain.models.User;
import com.bytes.bytes.contexts.shared.dtos.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Cozinha", description = "Operações realizadas pelo estabelicimento")
@RequestMapping("/kitchen/user")
public class UserController {
    private final UserService saveUserUseCase;

    private final UserMapper userMapper;

    public UserController(UserService saveUserUseCase, UserMapper userMapper) {
        this.saveUserUseCase = saveUserUseCase;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Cria usuário")
    @PostMapping()
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequest userRequest){
      try {
          UserDTO user = saveUserUseCase.createUser(userMapper.userRequestToUserEntityMapper(userRequest));
          return ResponseEntity.ok().body(user);
      } catch(Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
      }
    }


    @Operation(summary = "Atualiza usuário")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id,@Valid @RequestBody UserRequest userRequest){
      try {
          UserDTO user = saveUserUseCase.update(id, userMapper.userRequestToUserEntityMapper(userRequest));
          return ResponseEntity.ok().body(user);
      } catch(Exception e) {
          return ResponseEntity.badRequest().body(e.getMessage());
      }
    }

    @Operation(summary = "Desativa usuário")
    @PutMapping("disable/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        try {
            UserDTO user = saveUserUseCase.delete(id);
            return ResponseEntity.ok().body(user);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Autentica usuário")
    @PostMapping("autenticate")
    public ResponseEntity<Object> autenticate(String email, String password){
        try {
            String token = saveUserUseCase.autenticate(email, password);
            return ResponseEntity.ok().body(token);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
