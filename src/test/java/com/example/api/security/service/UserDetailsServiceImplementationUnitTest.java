package com.example.api.security.service;

import com.example.api.security.model.User;
import com.example.api.security.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplementationUnitTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserDetailsServiceImplementation underTest;
    @Test
    void shouldLoadUserByUsername() {
        User user = new User("test_username", "test_password");

        when(userRepository.findByUsername(anyString())).thenReturn(user);

        UserDetails resultUser = underTest.loadUserByUsername("test_username");

        assertThat(resultUser).usingRecursiveComparison().isEqualTo(user);
        verify(userRepository, times(1)).findByUsername(anyString());
        verifyNoMoreInteractions(userRepository);
    }
}