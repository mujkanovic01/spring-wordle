package com.example.springwordle.core.repository;

import com.example.springwordle.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findFirstByEmailLike(String emailPattern);

    // Find by username
    Optional<User> findByUsername(String username);
}