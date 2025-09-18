package com.clinica.backend.Service;

import com.clinica.backend.FactoryTest.UserFactory;
import com.clinica.backend.dto.User;
import com.clinica.backend.repository.UserRepository;
import com.clinica.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldRegisterUserSuccessfully(){
        User user = UserFactory.createUser("joao", "1234", "joao", "USER");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User saved = userService.save(user);
        assertEquals("joao", saved.getUsername());
    }

    @Test
    void shouldRegisterUserFail(){
        User user = UserFactory.createUser(null, "1234", "joao", "USER");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User saved = userService.save(user);
        assertNull(null, saved.getUsername());
    }




}
