package com.eshop.authentication.services;

import com.eshop.authentication.entities.AuthRequest;
import com.eshop.authentication.entities.AuthResponse;
import com.eshop.authentication.entities.UserValueObject;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    public AuthResponse register(AuthRequest request){

        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        UserValueObject registeredUser = restTemplate.postForObject("http://EshopUser/users/new", request, UserValueObject.class);

        if(registeredUser != null){
            String accessToken = jwtUtil.generate(registeredUser.getUserId(), registeredUser.getRole(), "ACCESS");
            String refreshToken = jwtUtil.generate(registeredUser.getUserId(), registeredUser.getRole(), "REFRESH");

            return new AuthResponse(accessToken, refreshToken);
        } else {
            throw new RuntimeException("Registration error");
        }

    }

    public AuthResponse login(AuthRequest request){

        UserValueObject user = restTemplate.postForObject("http://EshopUser/users/login", request, UserValueObject.class);

        if(user != null){

            if(BCrypt.checkpw(request.getPassword(), user.getPassword())){
                String accessToken = jwtUtil.generate(user.getUserId(), user.getRole(), "ACCESS");
                String refreshToken = jwtUtil.generate(user.getUserId(), user.getRole(), "REFRESH");

                return new AuthResponse(accessToken, refreshToken);
            } else {
                throw new RuntimeException("Wrong Password");
            }

        } else {
            throw new RuntimeException("The user isn't present in the Database!");
        }
    }
}
