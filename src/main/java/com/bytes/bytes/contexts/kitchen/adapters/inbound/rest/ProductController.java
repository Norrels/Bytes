package com.bytes.bytes.contexts.kitchen.adapters.inbound.rest;

import com.bytes.bytes.contexts.kitchen.adapters.inbound.dtos.ProductRequest;
import com.bytes.bytes.contexts.kitchen.application.services.ProductService;
import com.bytes.bytes.contexts.kitchen.application.services.UserService;
import com.bytes.bytes.contexts.kitchen.utils.ProductMapper;
import com.bytes.bytes.contexts.shared.dtos.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Cozinha", description = "Operações realizadas pelo estabelicimento")
@RequestMapping("/kitchen/product")
public class ProductController {
    private final UserService userService;

    private final ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    public ProductController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Operation(summary = "Cria produto")
    @PostMapping()
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductRequest productRequest){
      try {
          var aa = productMapper.toProductDTO(productRequest);
          aa.setCreatedById(2L);
          ProductDTO productDTO = productService.createProduct(aa);
          return ResponseEntity.ok().body(productDTO);
      } catch(Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
      }
    }


//    @Operation(summary = "Atualiza usuário")
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateUser(@PathVariable Long id,@Valid @RequestBody UserRequest userRequest){
//      try {
//          UserDTO user = userService.update(id, userMapper.userRequestToUserDTOMapper(userRequest));
//          return ResponseEntity.ok().body(user);
//      } catch(Exception e) {
//          return ResponseEntity.badRequest().body(e.getMessage());
//      }
//    }
//
//    @Operation(summary = "Desativa usuário")
//    @PutMapping("disable/{id}")
//    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
//        try {
//            UserDTO user = userService.delete(id);
//            return ResponseEntity.ok().body(user);
//        } catch(Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @Operation(summary = "Autentica usuário")
//    @PostMapping("autenticate")
//    public ResponseEntity<Object> autenticate(String email, String password){
//        try {
//            String token = userService.autenticate(email, password);
//            return ResponseEntity.ok().body(token);
//        } catch(Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

}
