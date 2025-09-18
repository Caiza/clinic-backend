package com.clinica.backend.Repository;

import com.clinica.backend.FactoryTest.UserFactory;
import com.clinica.backend.dto.User;
import com.clinica.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoyTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldRegisterByUsename(){
        User user = UserFactory.createUser("Tete", "1234", "maria joao", "USER");

        userRepository.save(user);
        User found = userRepository.findByUsername("Tete").orElse(null);
        assertNotNull(found);
        assertEquals("Tete", found.getUsername());
    }

    @Test
    void shouldReturnEmptyWhenUserNotFound(){
        Optional<User> result = userRepository.findByUsername("zero");
        assertTrue(result.isEmpty());
    }


}
