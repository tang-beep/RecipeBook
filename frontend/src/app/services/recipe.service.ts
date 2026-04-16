import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recipe, RecipeCreate, Season } from '../models/recipe.model';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private apiUrl = 'http://localhost:8080/api/recipes';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.apiUrl);
  }

  getById(id: number): Observable<Recipe> {
    return this.http.get<Recipe>(`${this.apiUrl}/${id}`);
  }

  create(recipe: RecipeCreate): Observable<Recipe> {
    return this.http.post<Recipe>(this.apiUrl, recipe);
  }

  update(id: number, recipe: RecipeCreate): Observable<Recipe> {
    return this.http.put<Recipe>(`${this.apiUrl}/${id}`, recipe);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  searchByTitle(title: string): Observable<Recipe[]> {
    const params = new HttpParams().set('title', title);
    return this.http.get<Recipe[]>(`${this.apiUrl}/search/title`, { params });
  }

  searchBySeason(season: Season): Observable<Recipe[]> {
    const params = new HttpParams().set('season', season);
    return this.http.get<Recipe[]>(`${this.apiUrl}/search/season`, { params });
  }

  searchByTime(maxTime: number): Observable<Recipe[]> {
    const params = new HttpParams().set('maxTime', maxTime);
    return this.http.get<Recipe[]>(`${this.apiUrl}/search/time`, { params });
  }

  searchByIngredients(ingredients: string[]): Observable<Recipe[]> {
    const params = new HttpParams().set('ingredients', ingredients.join(','));
    return this.http.get<Recipe[]>(`${this.apiUrl}/search/ingredients`, { params });
  }
}
