package com.eshop.user.services;

import com.eshop.user.entities.UserValueObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public UserValueObject save(UserValueObject userVO) {
        UserValueObject user = new UserValueObject();
        return user;
    }
}
