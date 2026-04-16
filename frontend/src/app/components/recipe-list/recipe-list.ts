import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { RecipeService } from '../../services/recipe.service';
import { Recipe } from '../../models/recipe.model';

@Component({
  selector: 'app-recipe-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './recipe-list.html',
  styleUrls: ['./recipe-list.css']
})
export class RecipeList implements OnInit {

  recipes = signal<Recipe[]>([]);
  loading = signal(true);
  error = signal<string | null>(null);

  constructor(private recipeService: RecipeService) {}

  ngOnInit(): void {
    this.loadRecipes();
  }

  loadRecipes(): void {
    this.loading.set(true);
    this.error.set(null);

    this.recipeService.getAll().subscribe({
      next: (recipes) => {
        this.recipes.set(recipes);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Erreur lors du chargement des recettes');
        this.loading.set(false);
      }
    });
  }

  delete(id: number, event: Event): void {
    event.stopPropagation();

    if (!confirm('Supprimer cette recette ?')) return;

    this.recipeService.delete(id).subscribe({
      next: () => {
        this.recipes.update(list => list.filter(r => r.id !== id));
      },
      error: () => {
        this.error.set('Erreur lors de la suppression');
      }
    });
  }
}
