package com.eshop.authentication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class AuthRequest {

    private String email;
    private String password;
}
