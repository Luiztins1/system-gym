package com.github.Luiztins1.repository;

import com.github.Luiztins1.model.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAuthRepository extends JpaRepository<UserAuth, UUID> {
    UserAuth findByLogin(String login);
}
