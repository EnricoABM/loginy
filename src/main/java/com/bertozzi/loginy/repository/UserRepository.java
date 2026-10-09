package com.bertozzi.loginy.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bertozzi.loginy.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, UUID> {
    
    Optional<UserModel> findByEmail(String name);
}
