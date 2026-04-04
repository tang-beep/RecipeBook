package com.tangbeep.recipebook.dtos;

import lombok.Data;

@Data
public class RecipeIngredientDto {
    private Long id;
    private String name;
    private Double quantity;
    private String unit;
}