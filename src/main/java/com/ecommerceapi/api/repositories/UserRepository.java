package com.ecommerceapi.api.repositories;

import com.ecommerceapi.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByEmail(String email);

    @Query("SELECT u.id " +
            "FROM User u " +
            "WHERE u.email = :email")
    Optional<UUID> findUserIdByEmail(@Param("email") String email);
}
