package com.secureapi.secureapi.service;

import com.secureapi.secureapi.dto.AuthResponse;
import com.secureapi.secureapi.model.User;
import com.secureapi.secureapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Service
public class AuthService {

    private final Key SECRET = Keys.hmacShaKeyFor(
        "mySuperSecretKeyThatIsAtLeast32CharactersLong!".getBytes()
    );
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public AuthResponse register(String username, String password) {

        if (repository.exists(username)) {
            return new AuthResponse("Usuario ya existe ❌", false, null, null);
        }

        String hashed = encoder.encode(password);
        repository.save(new User(username, hashed));

        String token = generateToken(username);

        return new AuthResponse("Usuario registrado correctamente ✅", true, username, token);
    }

    public AuthResponse login(String username, String password) {

        User user = repository.findByUsername(username);

        if (user == null) {
            return new AuthResponse("Usuario no existe ❌", false, null, null);
        }

        if (encoder.matches(password, user.getPassword())) {

            String token = generateToken(username);

            return new AuthResponse("Login exitoso ✅", true, username, token);
        }

        return new AuthResponse("Contraseña incorrecta ❌", false, null, null);
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SECRET, SignatureAlgorithm.HS256)
                .compact();
    }

}