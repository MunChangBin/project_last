package com.example.test11.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    public SiteUser create(String email , String password) {
        SiteUser user = new SiteUser();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode((password)));
        this.userRepository.save(user);
        return user;
    }
}
