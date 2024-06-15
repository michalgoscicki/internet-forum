package com.internet_forum.springboot.repository;


import com.internet_forum.springboot.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<UserEntity> findById(Long integer);

    Optional<UserEntity> findByEmail(String email);
}
