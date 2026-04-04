package com.tangbeep.recipebook.dtos;

import lombok.Data;

@Data
public class RecipePhotoDto {
    private Long id;
    private String url;
    private String caption;
}