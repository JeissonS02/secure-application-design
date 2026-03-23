package com.secureapi.secureapi.controller;


import com.secureapi.secureapi.dto.*;
import com.secureapi.secureapi.service.AuthService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return service.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {
        return service.register(request.getUsername(), request.getPassword());
    }
}