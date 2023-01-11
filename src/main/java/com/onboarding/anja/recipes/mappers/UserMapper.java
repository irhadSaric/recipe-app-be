package com.onboarding.anja.recipes.mappers;

import com.onboarding.anja.recipes.entity.RecipeEntity;
import com.onboarding.anja.recipes.entity.UserEntity;
import com.onboarding.anja.recipes.projections.CreateUserProjection;
import com.onboarding.anja.recipes.projections.RecipeProjection;
import com.onboarding.anja.recipes.projections.UserProjection;
import com.onboarding.anja.recipes.projections.UserWithoutRecipesProjection;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserProjection toUserProjection(UserEntity userEntity) {
        UserProjection userProjection = new UserProjection();
        userProjection.setId(userEntity.getId());
        userProjection.setFirstName(userEntity.getFirstName());
        userProjection.setLastName(userEntity.getLastName());

        List<RecipeEntity> entityRecipes = userEntity.getRecipes();
        List<RecipeProjection> result = entityRecipes.stream().map(RecipeMapper::toProjection).collect(Collectors.toList());

        userProjection.setRecipes(result);

        return userProjection;
    }

    public static UserWithoutRecipesProjection toUserWithoutRecipesProjection(UserEntity userEntity) {
        UserWithoutRecipesProjection userProjection = new UserWithoutRecipesProjection();
        userProjection.setId(userEntity.getId());
        userProjection.setFirstName(userEntity.getFirstName());
        userProjection.setLastName(userEntity.getLastName());

        return userProjection;
    }

    public static UserEntity toUserEntity(CreateUserProjection userProjection) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userProjection.getEmail());
        userEntity.setFirstName(userProjection.getFirstName());
        userEntity.setLastName(userProjection.getLastName());
        userEntity.setPassword(userProjection.getPassword());

        return userEntity;
    }

    public static UserEntity toUserEntity(UserProjection userProjection) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userProjection.getFirstName());
        userEntity.setLastName(userProjection.getLastName());

        return userEntity;
    }
}
