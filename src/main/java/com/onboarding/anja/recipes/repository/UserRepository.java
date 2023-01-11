package com.onboarding.anja.recipes.repository;

import com.onboarding.anja.recipes.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
//    @Query("select u from UserEntity u where u.firstName=:firstName")
//    UserEntity findAllUsersWithRecipePizza(String firstName);
}
