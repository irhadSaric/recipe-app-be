package com.onboarding.anja.recipes.projections;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateRecipeProjection {
    @NotNull
    private String description;
    @NotNull
    private Long userId;
    private List<CreateIngredientListProjection> ingredients;

    public CreateRecipeProjection() {
    }

    public CreateRecipeProjection(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CreateIngredientListProjection> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<CreateIngredientListProjection> ingredients) {
        this.ingredients = ingredients;
    }
}
