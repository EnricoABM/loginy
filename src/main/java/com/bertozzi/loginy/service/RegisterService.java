package com.bertozzi.loginy.service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bertozzi.loginy.model.UserModel;
import com.bertozzi.loginy.repository.UserRepository;

/**
 * Service para gerenciar registro de novos usuários
 * 
 * @author Enrico Bertozzi
 * @version 1.0
 * @see {@link UserModel}
 */
@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisPendingRegister pendingRegister;
    private final EmailRegisterSender emailSender;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            RedisPendingRegister pendingRegister, EmailRegisterSender emailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.pendingRegister = pendingRegister;
        this.emailSender = emailSender;
    }

    public UserModel save(String email, String password, String code) {
        Optional<PendingRegistration> opt = pendingRegister.findByEmail(email);
        if (opt.isEmpty()) {
            throw new RuntimeException();
        }

        UserModel model = opt.get().toModel();
        model.setPassword(passwordEncoder.encode(password));
        model.setId(UUID.randomUUID());

        pendingRegister.deleteByEmail(email);

        return userRepository.save(model);
    }

    @PreAuthorize("hasAuthority('USER_CREATE')")
    public void preRegister(UserModel model) {
        Optional<UserModel> opt = userRepository.findByEmail(model.getEmail());
        if (opt.isPresent()) {
            throw new DuplicateKeyException("Usuário já cadastrado");
        }

        pendingRegister.save(PendingRegistration.from(model));
        emailSender.send(model.getEmail(), generateCode());
    }

    private String generateCode() {
        return String.valueOf(
                100000 + new SecureRandom().nextInt(900000));
    }

}
