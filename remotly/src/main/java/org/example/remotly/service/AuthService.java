package org.example.remotly.service;

import org.example.remotly.response.AuthResponse;
import org.example.remotly.response.LoginRequest;
import org.example.remotly.response.SignUpRequest;

public interface AuthService {

    void sentLoginOtp(String email ) throws Exception;
    String createUser(SignUpRequest signUpRequest) throws Exception;
    AuthResponse signIn(LoginRequest request);
}
