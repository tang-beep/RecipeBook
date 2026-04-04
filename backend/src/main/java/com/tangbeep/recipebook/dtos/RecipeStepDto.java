package com.tangbeep.recipebook.dtos;

import lombok.Data;

@Data
public class RecipeStepDto {
    private Long id;
    private Integer stepOrder;
    private String description;
}