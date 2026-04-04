package com.tangbeep.recipebook.dtos;

import com.tangbeep.recipebook.models.Recipe;
import com.tangbeep.recipebook.models.RecipeIngredient;
import com.tangbeep.recipebook.models.RecipePhoto;
import com.tangbeep.recipebook.models.RecipeStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeMapper {

    public RecipeDto toDto(Recipe recipe) {
        RecipeDto dto = new RecipeDto();
        dto.setId(recipe.getId());
        dto.setTitle(recipe.getTitle());
        dto.setDescription(recipe.getDescription());
        dto.setPrepTime(recipe.getPrepTime());
        dto.setCookTime(recipe.getCookTime());
        dto.setServings(recipe.getServings());
        dto.setSeason(recipe.getSeason());

        // Champ calculé
        if (recipe.getPrepTime() != null && recipe.getCookTime() != null) {
            dto.setTotalTime(recipe.getPrepTime() + recipe.getCookTime());
        }

        dto.setIngredients(recipe.getIngredients().stream()
                .map(this::toIngredientDto)
                .toList());

        dto.setSteps(recipe.getSteps().stream()
                .map(this::toStepDto)
                .toList());

        dto.setPhotos(recipe.getPhotos().stream()
                .map(this::toPhotoDto)
                .toList());

        return dto;
    }

    public Recipe toEntity(RecipeCreateDto dto) {
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setDescription(dto.getDescription());
        recipe.setPrepTime(dto.getPrepTime());
        recipe.setCookTime(dto.getCookTime());
        recipe.setServings(dto.getServings());
        recipe.setSeason(dto.getSeason());

        dto.getIngredients().forEach(i -> {
            RecipeIngredient ingredient = new RecipeIngredient();
            ingredient.setName(i.getName());
            ingredient.setQuantity(i.getQuantity());
            ingredient.setUnit(i.getUnit());
            ingredient.setRecipe(recipe);
            recipe.getIngredients().add(ingredient);
        });

        dto.getSteps().forEach(s -> {
            RecipeStep step = new RecipeStep();
            step.setStepOrder(s.getStepOrder());
            step.setDescription(s.getDescription());
            step.setRecipe(recipe);
            recipe.getSteps().add(step);
        });

        dto.getPhotos().forEach(p -> {
            RecipePhoto photo = new RecipePhoto();
            photo.setUrl(p.getUrl());
            photo.setCaption(p.getCaption());
            photo.setRecipe(recipe);
            recipe.getPhotos().add(photo);
        });

        return recipe;
    }

    private RecipeIngredientDto toIngredientDto(RecipeIngredient i) {
        RecipeIngredientDto dto = new RecipeIngredientDto();
        dto.setId(i.getId());
        dto.setName(i.getName());
        dto.setQuantity(i.getQuantity());
        dto.setUnit(i.getUnit());
        return dto;
    }

    private RecipeStepDto toStepDto(RecipeStep s) {
        RecipeStepDto dto = new RecipeStepDto();
        dto.setId(s.getId());
        dto.setStepOrder(s.getStepOrder());
        dto.setDescription(s.getDescription());
        return dto;
    }

    private RecipePhotoDto toPhotoDto(RecipePhoto p) {
        RecipePhotoDto dto = new RecipePhotoDto();
        dto.setId(p.getId());
        dto.setUrl(p.getUrl());
        dto.setCaption(p.getCaption());
        return dto;
    }
}