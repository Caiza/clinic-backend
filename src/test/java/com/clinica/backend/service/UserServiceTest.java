package com.clinica.backend.service;

import com.clinica.backend.user.User;
import com.clinica.backend.factoryTest.UserFactory;
import com.clinica.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldRegisterUserSuccessfully() {
        User user = UserFactory.createUser("joao", "1234", "joao", "USER");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User saved = userService.save(user);
        assertEquals("joao", saved.getUsername());
    }

    @Test
    void shouldRegisterUserFail() {
        User user = UserFactory.createUser(null, "1234", "joao", "USER");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User saved = userService.save(user);
        assertNull(null, saved.getUsername());
    }


}
