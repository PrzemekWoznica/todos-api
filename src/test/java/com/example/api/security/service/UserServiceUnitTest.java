package com.example.api.security.service;

import com.example.api.security.error.UserAlreadyExistsException;
import com.example.api.security.jwt.JwtUtils;
import com.example.api.security.model.User;
import com.example.api.security.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {
    @Mock
    UserRepository userRepository;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    JwtUtils jwtUtils;
    @InjectMocks
    UserService userService;
    @Test
    void shouldSignup() {
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("test_password");
        when(userRepository.save(any(User.class))).thenReturn(any(User.class));

        userService.signup("test_username", "test_password");

        verify(userRepository, times(1)).findByUsername(anyString());
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(anyString());
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(passwordEncoder);
    }

    @Test
    void shouldThrowUserAlreadyExistException() {
        User user = new User("test_username", "test_password");
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        assertThatThrownBy(() -> {
            userService.signup("test_username", "test_password");
        }).isInstanceOf(UserAlreadyExistsException.class);

        verify(userRepository, times(1)).findByUsername(anyString());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldSignin() {

    }

    @Test
    void getUserFromAuthentication() {
    }
}