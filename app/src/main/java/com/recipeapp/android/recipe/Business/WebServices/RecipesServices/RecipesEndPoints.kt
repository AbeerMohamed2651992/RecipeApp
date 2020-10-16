package com.recipeapp.android.recipe.Business.WebServices.RecipesServices

/**
 * Created by DELL on 10/14/2020.
 */
import com.recipeapp.android.recipe.Business.Common.Common
import com.recipeapp.android.recipe.Business.Models.Recipes
import com.recipeapp.android.recipe.Business.Models.RecipesItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RecipesEndPoints {
    @GET("android-test/recipes.json")
    fun getRecipes(): Call<List<RecipesItem>>
}