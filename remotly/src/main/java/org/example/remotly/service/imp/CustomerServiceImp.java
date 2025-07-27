package org.example.remotly.service.imp;

import lombok.RequiredArgsConstructor;
import org.example.remotly.domain.UserRole;
import org.example.remotly.model.Seller;
import org.example.remotly.model.User;
import org.example.remotly.repository.SellerRepository;
import org.example.remotly.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements UserDetailsService {

    private static final String SELLER_PREFIX = "Seller_";

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (username.startsWith(SELLER_PREFIX)) {
//            String actualUsername = username.substring(SELLER_PREFIX.length());
//            Seller seller = sellerRepository.findByEmail(actualUsername);
//            if (seller == null) {
//                return buildUserDetails(seller.getEmail(),seller.getPassword(),seller.getRole());
//            }
//        }else{
//            User user = userRepository.findByEmail(username);
//            if (user == null) {
//                return buildUserDetails(user.getEmail(),
//                        user.getPassword(),user.getRole());
//            }
//        }
//        throw new UsernameNotFoundException("User not found"+username);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith(SELLER_PREFIX)) {
            String actualUsername = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepository.findByEmail(actualUsername);
            if (seller != null) {
                return buildUserDetails(seller.getEmail(), seller.getPassword(), seller.getRole());
            }
        } else {
            User user = userRepository.findByEmail(username);
            if (user != null) {
                return buildUserDetails(user.getEmail(), user.getPassword(), user.getRole());
            }
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }


    private UserDetails buildUserDetails(String email, String password, UserRole role) {
        if(role == null) role = UserRole.ROLE_CUSTOMER ;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(email, password, grantedAuthorities);
    }
}
