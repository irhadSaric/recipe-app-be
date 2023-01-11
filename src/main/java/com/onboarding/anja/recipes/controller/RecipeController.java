package com.onboarding.anja.recipes.controller;

import com.onboarding.anja.recipes.entity.RecipeEntity;
import com.onboarding.anja.recipes.projections.CreateRecipeProjection;
import com.onboarding.anja.recipes.projections.RecipeProjection;
import com.onboarding.anja.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/recipe")
@Validated
@CrossOrigin("http://localhost:3001")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public RecipeProjection createNewRecipe(@Valid @RequestBody CreateRecipeProjection request) {
        return recipeService.createNewRecipe(request);
    }

    @GetMapping("/{id}")
    public RecipeProjection getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }

}
