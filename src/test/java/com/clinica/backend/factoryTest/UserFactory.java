package com.clinica.backend.factoryTest;

import com.clinica.backend.user.Role;
import com.clinica.backend.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class UserFactory {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User createUser(String username, String rawPassword, String name, Role role) {
        return new User().builder().username(username).password(rawPassword).name(name).role(role).build();
    }
}
