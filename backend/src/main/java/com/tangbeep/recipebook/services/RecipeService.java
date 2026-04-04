package com.tangbeep.recipebook.services;

import com.tangbeep.recipebook.models.Recipe;
import com.tangbeep.recipebook.models.Season;
import com.tangbeep.recipebook.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tangbeep.recipebook.dtos.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public List<RecipeDto> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    public RecipeDto getRecipeById(Long id) {
        return recipeMapper.toDto(findById(id));
    }

    public List<RecipeDto> searchByTitle(String title) {
        return recipeRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    public List<RecipeDto> searchBySeason(Season season) {
        return recipeRepository.findBySeasonOrSeason(season, Season.ALL).stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    public List<RecipeDto> searchByMaxTime(Integer maxTime) {
        return recipeRepository.findByTotalTimeLessThanEqual(maxTime).stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    public List<RecipeDto> searchByIngredients(List<String> ingredients) {
        List<String> lowerCased = ingredients.stream()
                .map(String::toLowerCase)
                .toList();
        return recipeRepository.findByIngredientNames(lowerCased).stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    @Transactional
    public RecipeDto createRecipe(RecipeCreateDto dto) {
        Recipe recipe = recipeMapper.toEntity(dto);
        return recipeMapper.toDto(recipeRepository.save(recipe));
    }

    @Transactional
    public RecipeDto updateRecipe(Long id, RecipeCreateDto dto) {
        Recipe existing = findById(id);
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setPrepTime(dto.getPrepTime());
        existing.setCookTime(dto.getCookTime());
        existing.setServings(dto.getServings());
        existing.setSeason(dto.getSeason());
        existing.getIngredients().clear();
        existing.getSteps().clear();
        existing.getPhotos().clear();
        Recipe updated = recipeMapper.toEntity(dto);
        existing.getIngredients().addAll(updated.getIngredients());
        existing.getSteps().addAll(updated.getSteps());
        existing.getPhotos().addAll(updated.getPhotos());
        existing.getIngredients().forEach(i -> i.setRecipe(existing));
        existing.getSteps().forEach(s -> s.setRecipe(existing));
        existing.getPhotos().forEach(p -> p.setRecipe(existing));
        return recipeMapper.toDto(recipeRepository.save(existing));
    }

    @Transactional
    public void deleteRecipe(Long id) {
        recipeRepository.delete(findById(id));
    }

    private Recipe findById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recette introuvable : " + id));
    }
}