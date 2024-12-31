package com.bytes.bytes.contexts.kitchen.adapters.inbound.rest;

import com.bytes.bytes.contexts.kitchen.adapters.inbound.dtos.TokenDTO;
import com.bytes.bytes.contexts.kitchen.adapters.inbound.dtos.UserRequest;
import com.bytes.bytes.contexts.kitchen.domain.models.User;
import com.bytes.bytes.contexts.kitchen.utils.UserMapper;
import com.bytes.bytes.contexts.kitchen.application.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User", description = "Controle de usuários do estabelicimento")
@RequestMapping("/kitchen/user")
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService saveUserUseCase, UserMapper userMapper) {
        this.userService = saveUserUseCase;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Cria usuário")
    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody UserRequest userRequest){
      try {
          User user = userService.createUser(userMapper.toUser(userRequest));
          return ResponseEntity.ok().body(user);
      } catch(Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
      }
    }

    @Operation(summary = "Atualiza usuário")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,@Valid @RequestBody UserRequest userRequest){
      try {
          User user = userService.update(id, userMapper.toUser(userRequest));
          return ResponseEntity.ok().body(user);
      } catch(Exception e) {
          return ResponseEntity.badRequest().body(e.getMessage());
      }
    }

    @Operation(summary = "Desativa usuário")
    @PutMapping("disable/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try {
            userService.delete(id);
            return ResponseEntity.ok().body("Usuário desativado com sucesso");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Autentica usuário")
    @PostMapping("autenticate")
    public ResponseEntity<Object> autenticate(String email, String password){
        try {
            String token = userService.autenticate(email, password);
            return ResponseEntity.ok().body(new TokenDTO(token));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
