package com.example.api.security.service;

import com.example.api.security.error.IllegalUsernameUsedException;
import com.example.api.security.error.UserAlreadyExistsException;
import com.example.api.security.jwt.JwtUtils;
import com.example.api.security.model.User;
import com.example.api.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;

    AuthenticationManager authenticationManager;

    PasswordEncoder passwordEncoder;

    JwtUtils jwtUtils;

    public User signup(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new UserAlreadyExistsException("User with username " + username + " already exists");
        }

        User _user = new User(username, passwordEncoder.encode(password));
        return userRepository.save(_user);
    }

    public String signin(String username, String password) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authenticate.isAuthenticated()){
            String token = jwtUtils.createToken(username);
            return token;
        } else {
            throw new IllegalUsernameUsedException("User " + username + " is not authorized");
        }

    }

    public User getUserFromAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }
}
