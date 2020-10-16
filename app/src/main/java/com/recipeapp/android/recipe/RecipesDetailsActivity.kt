package com.recipeapp.android.recipe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_recipes_details.*
import java.util.ArrayList

/**
 * Created by Abeer on 10/15/2020.
 */
class RecipesDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_details)

        //get recipes details from Intent
        val arr: ArrayList<String> = intent.getStringArrayListExtra("recipedetails")
        if(arr.count()==9) {
            val Name: String = arr[0];
            val Calories: String = arr[1]
            val Carbos: String = arr[2]
            val Description: String = arr[3]
            val Difficulty: String = arr[4]
            val Fats: String = arr[5]
            val Headline: String = arr[6]
            val Proteins: String = arr[7]
            val Time: String = arr[8]

            //set each item to textView
            txtrecipenameitem.setText(Name)
            txtrecipecaloriesitem.setText(Calories)
            txtrecipecarositem.setText(Carbos)
            txtrecipedescitem.setText(Description)
            txtrecipediffitem.setText(Difficulty)
            txtrecipefatsitem.setText(Fats)
            txtrecipeheadlineitem.setText(Headline)
            txtrecipeproteinsitem.setText(Proteins)
            txtrecipetimeitem.setText(Time)
        }
    }
}