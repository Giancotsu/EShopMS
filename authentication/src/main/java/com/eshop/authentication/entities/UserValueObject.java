package com.eshop.authentication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserValueObject {

    private String id;
    private String email;
    private String password;
    private String role;
}
