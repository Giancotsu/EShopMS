package com.eshop.user.services;

import com.eshop.user.dto.LoginRequest;
import com.eshop.user.entities.UserEntity;
import com.eshop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity saveUser(UserEntity user) {

        if(userRepository.findByEmail(user.getEmail()).isEmpty()){
            user.setRole("USER");
            return userRepository.save(user);
        }else{
            throw new RuntimeException("Email already exists!");
        }

    }

    public UserEntity login(LoginRequest user) {

        Optional<UserEntity> loggedUser = userRepository.findByEmail(user.getEmail());

        if(loggedUser.isPresent()){
            return loggedUser.get();
        }else{
            throw new RuntimeException("User not found");
        }
    }
}
