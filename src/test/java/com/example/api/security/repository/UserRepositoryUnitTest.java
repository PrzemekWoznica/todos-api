package com.example.api.security.repository;

import com.example.api.security.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryUnitTest {
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user1 = new User("test_username_1", "test_password_1");
        userRepository.save(user1);
        User user2 = new User("test_username_2", "test_password_2");
        userRepository.save(user2);
    }

    @Test
    void shouldFindUserByUsername() {
        User resultUser1 = userRepository.findByUsername("test_username_1");
        User resultUser2 = userRepository.findByUsername("test_username_2");

        assertThat(resultUser1).extracting(User::getPassword).isEqualTo("test_password_1");
        assertThat(resultUser2).extracting(User::getPassword).isEqualTo("test_password_2");
    }
}