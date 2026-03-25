package com.example.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.RegisterRequest;
import com.example.auth.entities.User;
import com.example.auth.repositories.UserRepository;
import com.example.auth.services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository repository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // 1. Crear "token de intento"
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword());

        // 2. Validar credenciales
        Authentication auth = authenticationManager.authenticate(authToken);

        // 3. Si llegamos aquí → credenciales válidas
        UserDetails user = (UserDetails) auth.getPrincipal();

        // 4. Generar JWT con el email del usuario
        String token = jwtService.generateToken(user.getUsername());

        return ResponseEntity.ok().body(
                new java.util.HashMap<String, Object>() {
                    {
                        put("token", token);
                        put("email", user.getUsername());
                    }
                });
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        // 1. Validar si el email ya existe
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("El email ya está registrado");
        }

        // 2. Crear usuario nuevo
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 3. Guardarlo en BD
        repository.save(user);

        return ResponseEntity.ok("Usuario registrado correctamente");
    }

}
