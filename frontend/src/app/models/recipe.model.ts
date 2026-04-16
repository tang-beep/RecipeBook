export interface RecipeIngredient {
  id?: number;
  name: string;
  quantity: number;
  unit: string;
}

export interface RecipeStep {
  id?: number;
  stepOrder: number;
  description: string;
}

export interface RecipePhoto {
  id?: number;
  url: string;
  caption: string;
}

export type Season = 'SPRING' | 'SUMMER' | 'AUTUMN' | 'WINTER' | 'ALL';

export interface Recipe {
  id?: number;
  title: string;
  description: string;
  prepTime: number;
  cookTime: number;
  servings: number;
  season: Season;
  totalTime?: number;
  ingredients: RecipeIngredient[];
  steps: RecipeStep[];
  photos: RecipePhoto[];
}

export interface RecipeCreate {
  title: string;
  description: string;
  prepTime: number;
  cookTime: number;
  servings: number;
  season: Season;
  ingredients: RecipeIngredient[];
  steps: RecipeStep[];
  photos: RecipePhoto[];
}
