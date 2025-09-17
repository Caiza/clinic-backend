package com.clinica.backend.service;

import com.clinica.backend.dto.User;
import com.clinica.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User save(User user){
        return repository.save(user);
    }
    public Optional<User> findByUser(String username) { return repository.findByUsername(username); }


}
