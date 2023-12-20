package com.eshop.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserValueObject {

    private String userId;
    private String email;
    private String password;
    private String name;
}
