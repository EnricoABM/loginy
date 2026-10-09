package com.bertozzi.loginy.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.bertozzi.loginy.model.UserModel;
import com.bertozzi.loginy.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasAuthority('USERS_READ')")
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }
}
