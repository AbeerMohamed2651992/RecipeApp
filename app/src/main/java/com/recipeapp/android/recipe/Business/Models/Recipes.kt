package com.recipeapp.android.recipe.Business.Models

/**
 * Created by DELL on 10/14/2020.
 */
data class Recipes (
        val results: List<RecipesItem>
)

public class RecipesItem(
        //Getters and setters are auto-generated in Kotlin
        val calories : String,
        val carbos : String,
        val description : String,
        val difficulty : Int,
        val fats : String,
        val headline : String,
        val id : String,
        val image : String,
        val name : String,
        val proteins : String,
        val thumb : String,
        val time : String

)
