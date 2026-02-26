package com.CoreOne.Erp.auth.service;

import com.CoreOne.Erp.auth.dto.request.LoginRequest;
import com.CoreOne.Erp.auth.dto.request.RegisterRequest;
import com.CoreOne.Erp.auth.dto.response.TokenResponse;
import com.CoreOne.Erp.auth.model.User;
import com.CoreOne.Erp.auth.repositories.UserRepository;
import com.CoreOne.Erp.infra.enums.Role;
import com.CoreOne.Erp.infra.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public TokenResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));

        User user =
                userRepository.findByUsername(request.username()).orElseThrow(() -> new RuntimeException("Usuário não existe no sistema"));
        String token = jwtService.generateToken(user);
        return new TokenResponse(token);
    }

    public void register(RegisterRequest request){
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Uuário já existe");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setRole(Role.OPERATOR); // role padrão ao registrar

        userRepository.save(user);
    }
}
