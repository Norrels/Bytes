package com.bytes.bytes.contexts.shared.dtos;


import com.bytes.bytes.contexts.kitchen.domain.models.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private UserRole role;
    private String password;
    private boolean active;
}
