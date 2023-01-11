package com.onboarding.anja.recipes.service;

import com.onboarding.anja.recipes.entity.UserEntity;
import com.onboarding.anja.recipes.mappers.UserMapper;
import com.onboarding.anja.recipes.projections.CreateUserProjection;
import com.onboarding.anja.recipes.projections.LoginUserProjection;
import com.onboarding.anja.recipes.projections.UserProjection;
import com.onboarding.anja.recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserProjection> findAll() {
        List<UserEntity> allUsers = userRepository.findAll();

        List<UserProjection> result = allUsers.stream().map(UserMapper::toUserProjection).collect(Collectors.toList());

        return result;
    }

    public void createNewUser(CreateUserProjection request) {
        UserEntity userEntity = UserMapper.toUserEntity(request);
        UserEntity save = userRepository.save(userEntity);
    }

    public UserProjection findUserById(Long id) {
        Optional<UserEntity> userById = userRepository.findById(id);

        if(userById.isPresent()) {
            UserEntity userEntity = userById.get();
            return UserMapper.toUserProjection(userEntity);
        }

        return null;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public UserProjection updateUser(UserProjection request) {
        Optional<UserEntity> originalUser = userRepository.findById(request.getId());

        if(originalUser.isPresent()) {
            UserEntity userEntity = originalUser.get();
            userEntity.setFirstName(request.getFirstName());
            userEntity.setLastName(request.getLastName());
            UserEntity entity = userRepository.save(userEntity);
            return UserMapper.toUserProjection(entity);
        }

        return null;
    }

    public UserProjection login(LoginUserProjection request) {
        // 1. we need to fetch user from database by email
        Optional<UserEntity> byEmail = userRepository.findByEmail(request.getEmail());
        if (byEmail.isPresent()) {
            UserEntity userEntity = byEmail.get();
            // 2. we need to check if password from request is equals to stored password
            // 3. if yes return that user, if not return null
            if (request.getPassword().equals(userEntity.getPassword())) {
                return UserMapper.toUserProjection(userEntity);
            } else {
                return null;
            }
        }
        return null;
    }
}
