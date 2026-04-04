package com.tangbeep.recipebook.repositories;

import com.tangbeep.recipebook.models.Recipe;
import com.tangbeep.recipebook.models.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByTitleContainingIgnoreCase(String title);

    List<Recipe> findBySeason(Season season);

    List<Recipe> findBySeasonOrSeason(Season season, Season all);

    List<Recipe> findByCookTimeLessThanEqual(Integer maxTime);

    @Query("SELECT r FROM Recipe r WHERE (r.prepTime + r.cookTime) <= :maxTotalTime")
    List<Recipe> findByTotalTimeLessThanEqual(@Param("maxTotalTime") Integer maxTotalTime);

    @Query("""
        SELECT DISTINCT r FROM Recipe r
        JOIN r.ingredients i
        WHERE LOWER(i.name) IN :ingredientNames
    """)
    List<Recipe> findByIngredientNames(@Param("ingredientNames") List<String> ingredientNames);
}