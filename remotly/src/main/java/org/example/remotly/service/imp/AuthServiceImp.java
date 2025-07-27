package org.example.remotly.service.imp;

import lombok.RequiredArgsConstructor;
import org.example.remotly.config.JWTProvider;
import org.example.remotly.domain.UserRole;
import org.example.remotly.model.Cart;
import org.example.remotly.model.User;
import org.example.remotly.model.VerificationCode;
import org.example.remotly.repository.CartRepository;
import org.example.remotly.repository.UserRepository;
import org.example.remotly.repository.VerificationCodeRepository;
import org.example.remotly.response.AuthResponse;
import org.example.remotly.response.LoginRequest;
import org.example.remotly.response.SignUpRequest;
import org.example.remotly.service.AuthService;
import org.example.remotly.utiles.OtpUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JWTProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomerServiceImp customerService;

    @Override
    public void sentLoginOtp(String email) throws Exception {
        String SIGNING_PREFIX = "signin_";
        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());

            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new Exception("user not exist with provider email");
            }
        }
        VerificationCode isExist = verificationCodeRepository.findByEmail(email);
        if (isExist != null) {
            verificationCodeRepository.delete(isExist);
        }
        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setOtp(otp);
        verificationCodeRepository.save(verificationCode);

        String subject = "OTP verification";
        String text = "your otp is"+otp;

        emailService.sendVerificationEmail(email,otp,subject,text);

    }


//    @Override
//    public void sentLoginOtp(String email) throws Exception {
//        String SIGNING_PREFIX = "signin_";
//        if (email.startsWith(SIGNING_PREFIX)) {
//            email = email.substring(SIGNING_PREFIX.length());
//
//            User user = userRepository.findByEmail(email);
//            if (user == null) {
//                throw new Exception("user not exist with provider email");
//            }
//        }
//
//        VerificationCode isExist = verificationCodeRepository.findByEmail(email);
//        if (isExist != null) {
//            verificationCodeRepository.delete(isExist);
//        }
//
//        String otp = OtpUtil.generateOtp();
//
//        VerificationCode verificationCode = new VerificationCode();
//        verificationCode.setEmail(email);
//        verificationCode.setOtp(otp);
//        verificationCodeRepository.save(verificationCode);
//
//        String subject = "OTP verification";
//        String text = "your otp is " + otp;
//
//        emailService.sendVerificationEmail(email, otp, subject, text);
//    }


    @Override
    public String createUser(SignUpRequest signUpRequest) throws Exception {

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(signUpRequest.getEmail());
        if (verificationCode == null ||
                !verificationCode.getOtp().equals(signUpRequest.getOtp())) {
            throw new Exception("wrong otp......");
        }



        User existingUser = userRepository.findByEmail(signUpRequest.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("Email already in use");
        }

        User newUser = new User();
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setFullName(signUpRequest.getFullName());
        newUser.setRole(UserRole.ROLE_CUSTOMER);
        newUser.setPhoneNumber("01007809006");
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getOtp()));

        User savedUser = userRepository.save(newUser);

        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepository.save(cart);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(savedUser.getRole().toString()));

        Authentication auth = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(), null, authorities);

        SecurityContextHolder.getContext().setAuthentication(auth);

        return jwtProvider.generateToken(auth);
    }

    @Override
    public AuthResponse signIn(LoginRequest request) {
        String userName = request.getEmail();
        String otp = request.getOtp();

        Authentication authentication = authenticate(userName,otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login successful");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        authResponse.setUserRole(UserRole.valueOf(roleName));
        return authResponse;
    }

    private Authentication authenticate(String userName, String otp) {
        UserDetails userDetails = customerService.loadUserByUsername(userName);

        if(userDetails == null) {
            throw new BadCredentialsException("invalid username or password");
        }
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(userName);
        if(verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new BadCredentialsException("invalid otp");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }
}
