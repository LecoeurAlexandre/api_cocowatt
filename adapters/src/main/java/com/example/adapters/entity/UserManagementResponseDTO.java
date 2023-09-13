package com.example.adapters.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private boolean isAdmin;

}
