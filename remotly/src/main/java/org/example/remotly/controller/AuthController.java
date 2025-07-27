package org.example.remotly.controller;

import lombok.RequiredArgsConstructor;
import org.example.remotly.domain.UserRole;
import org.example.remotly.model.VerificationCode;
import org.example.remotly.repository.UserRepository;
import org.example.remotly.response.ApiResponse;
import org.example.remotly.response.AuthResponse;
import org.example.remotly.response.LoginRequest;
import org.example.remotly.response.SignUpRequest;
import org.example.remotly.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignUpRequest signUpRequest) throws Exception {

        String jwt = authService.createUser(signUpRequest);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("Successfully created user");
        response.setUserRole(UserRole.ROLE_CUSTOMER);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> createUserHandler(
            @RequestBody VerificationCode verificationCode) throws Exception {

        authService.sentLoginOtp(verificationCode.getEmail());
        ApiResponse response = new ApiResponse();
        response.setMessage("otp sent Successfully");
        return ResponseEntity.ok(response);

    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> LoginHandler(
            @RequestBody LoginRequest request) throws Exception {

       AuthResponse authResponse = authService.signIn(request);

        return ResponseEntity.ok(authResponse);

    }

    //
//    @PostMapping("/sent/login-signup-otp")
//    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//
//        try {
//            authService.sentLoginOtp(email);
//            Map<String, String> response = new HashMap<>();
//            response.put("message", "otp sent Successfully");
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            Map<String, String> error = new HashMap<>();
//            error.put("error", e.getMessage());
//            return ResponseEntity.badRequest().body(error);
//        }
//    }
}
