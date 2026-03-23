package com.secureapi.secureapi.dto;

public class AuthResponse {

    private String message;
    private boolean success;
    private String user;
    private String token;
    private String tokenType;

    public AuthResponse(String message, boolean success, String user, String token) {
        this.message = message;
        this.success = success;
        this.user = user;
        this.token = token;
        this.tokenType = "Bearer";
    }

    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }
    public String getUser() { return user; }
    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
}