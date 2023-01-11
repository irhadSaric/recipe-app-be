package com.onboarding.anja.recipes.projections;

public class IngredientWithQuantityProjection extends IngredientProjection {
    private Integer quantity;

    public IngredientWithQuantityProjection() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
