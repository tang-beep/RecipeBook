package com.tangbeep.recipebook.dtos;

import com.tangbeep.recipebook.models.Season;
import lombok.Data;
import java.util.List;

@Data
public class RecipeDto {
    private Long id;
    private String title;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private Season season;
    private Integer totalTime;
    private List<RecipeIngredientDto> ingredients;
    private List<RecipeStepDto> steps;
    private List<RecipePhotoDto> photos;
}