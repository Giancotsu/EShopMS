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
        UserValueObject registeredUser = restTemplate.postForObject("ESHOPUSER/users", request, UserValueObject.class);

        if(registeredUser != null){
            String accessToken = jwtUtil.generate(registeredUser.getId(), registeredUser.getRole(), "ACCESS");
            String refreshToken = jwtUtil.generate(registeredUser.getId(), registeredUser.getRole(), "REFRESH");

            return new AuthResponse(accessToken, refreshToken);
        } else {
            throw new RuntimeException("The user isn't present in the Database!");
        }

    }
}
