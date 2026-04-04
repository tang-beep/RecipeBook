package com.tangbeep.recipebook.dtos;

import com.tangbeep.recipebook.models.Season;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class RecipeCreateDto {

    @NotBlank
    private String title;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private Season season;
    private List<RecipeIngredientDto> ingredients = new ArrayList<>();
    private List<RecipeStepDto> steps = new ArrayList<>();
    private List<RecipePhotoDto> photos = new ArrayList<>();
}