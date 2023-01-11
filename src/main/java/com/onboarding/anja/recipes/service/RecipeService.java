package com.onboarding.anja.recipes.service;

import com.onboarding.anja.recipes.entity.IngredientEntity;
import com.onboarding.anja.recipes.entity.RecipeEntity;
import com.onboarding.anja.recipes.entity.RecipeIngredientEntity;
import com.onboarding.anja.recipes.entity.UserEntity;
import com.onboarding.anja.recipes.mappers.RecipeMapper;
import com.onboarding.anja.recipes.projections.CreateRecipeProjection;
import com.onboarding.anja.recipes.projections.RecipeProjection;
import com.onboarding.anja.recipes.repository.IngredientRepository;
import com.onboarding.anja.recipes.repository.RecipeIngredientRepository;
import com.onboarding.anja.recipes.repository.RecipeRepository;
import com.onboarding.anja.recipes.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    public RecipeProjection createNewRecipe(CreateRecipeProjection request) {
        RecipeEntity recipeEntity = RecipeMapper.toEntity(request);

        RecipeEntity entity = recipeRepository.save(recipeEntity);

        Optional<UserEntity> userById = userRepository.findById(request.getUserId());
        if(userById.isPresent()) {
            UserEntity userEntity = userById.get();
            userEntity.getRecipes().add(entity);

            UserEntity savedUser = userRepository.save(userEntity);

            entity.setUser(savedUser);
            recipeRepository.save(entity);
        }

        request.getIngredients().forEach(createIngredientListProjection -> {
            Optional<IngredientEntity> ingredientById = ingredientRepository.findById(createIngredientListProjection.getId());
            if(ingredientById.isPresent()) {
                IngredientEntity ingredientEntity = ingredientById.get();

                RecipeIngredientEntity recipeIngredientEntity = new RecipeIngredientEntity();
                recipeIngredientEntity.setRecipe(entity);
                recipeIngredientEntity.setIngredient(ingredientEntity);
                recipeIngredientEntity.setQuantity(createIngredientListProjection.getQuantity());

                RecipeIngredientEntity recipeIngredient = recipeIngredientRepository.save(recipeIngredientEntity);

                entity.getIngredients().add(recipeIngredient);
                recipeRepository.save(entity);
            }
        });

        return RecipeMapper.toProjection(entity);
    }

    public RecipeProjection getRecipeById(Long id) {
        Optional<RecipeEntity> recipeById = recipeRepository.findById(id);
        if(recipeById.isPresent()) {
            RecipeEntity recipe = recipeById.get();
            return RecipeMapper.toProjection(recipe);
        }

        return null;
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
