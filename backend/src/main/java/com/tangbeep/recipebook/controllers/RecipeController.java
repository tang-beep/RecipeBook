package com.tangbeep.recipebook.controllers;

import com.tangbeep.recipebook.models.Season;
import com.tangbeep.recipebook.services.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tangbeep.recipebook.dtos.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<RecipeDto>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(recipeService.searchByTitle(title));
    }

    @GetMapping("/search/season")
    public ResponseEntity<List<RecipeDto>> searchBySeason(@RequestParam Season season) {
        return ResponseEntity.ok(recipeService.searchBySeason(season));
    }

    @GetMapping("/search/time")
    public ResponseEntity<List<RecipeDto>> searchByTime(@RequestParam Integer maxTime) {
        return ResponseEntity.ok(recipeService.searchByMaxTime(maxTime));
    }

    @GetMapping("/search/ingredients")
    public ResponseEntity<List<RecipeDto>> searchByIngredients(@RequestParam List<String> ingredients) {
        return ResponseEntity.ok(recipeService.searchByIngredients(ingredients));
    }

    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@Valid @RequestBody RecipeCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable Long id, @Valid @RequestBody RecipeCreateDto dto) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}