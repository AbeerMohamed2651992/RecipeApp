package com.recipeapp.android.recipe

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.recipeapp.android.recipe.Business.Models.Recipes
import com.recipeapp.android.recipe.Business.WebServices.RecipesServices.RecipesEndPoints
import com.recipeapp.android.recipe.Business.WebServices.RecipesServices.ServiceBuilder
import android.view.View
import android.widget.*
import com.recipeapp.android.recipe.Business.Common.Common
import com.recipeapp.android.recipe.Business.Models.RecipesItem
import com.recipeapp.android.recipe.CustomAdapter.RecipesAdapter
import kotlinx.android.synthetic.main.activity_recipes_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import android.R.attr.name




/**
 * Created by Abeer on 10/14/2020.
 */
class RecipesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var radioGroup: RadioGroup
    private lateinit var selectedRadioButton: RadioButton

    private lateinit var recipes : List<RecipesItem>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_list)


        val common=Common();
        //get Recipes Data from API
        if(common.verifyAvailableNetwork(this)) {
            val request = ServiceBuilder.buildService(RecipesEndPoints::class.java)
            var call: Call<List<RecipesItem>>
            call = request.getRecipes()
            call.enqueue(object : Callback<List<RecipesItem>> {
                override fun onResponse(call: Call<List<RecipesItem>>, response: Response<List<RecipesItem>>) {
                    if (response.isSuccessful) {
                        viewManager = LinearLayoutManager(this@RecipesActivity)

                        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
                            viewAdapter = RecipesAdapter(response.body()!!)
                            recipes=response.body()!!

                            // use this setting to improve performance if you know that changes
                            // in content do not change the layout size of the RecyclerView
                            setHasFixedSize(true)

                            // use a linear layout manager
                            layoutManager = viewManager

                            // specify an viewAdapter
                            adapter = viewAdapter

                        }

                    }
                }

                override fun onFailure(call: Call<List<RecipesItem>>, t: Throwable) {
                    Toast.makeText(this@RecipesActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
        else{
            Toast.makeText(this@RecipesActivity, R.string.msg_nointernetconnection, Toast.LENGTH_LONG).show()
        }
        //The onQueryTextChange method called every time we typing on the SearchView
        //and updates the RecyclerView with the new results
        recipename_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (recyclerView.adapter  as Filterable).filter.filter(newText)
               // recyclerView.adapter.filter.filter(newText)
                return false
            }

        })


        //sort by calories or by fat
        radioGroup = findViewById(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            when (radio) {
                radiosortcalories -> {
                    Collections.sort(recipes, object : Comparator<RecipesItem> {
                        override fun compare(lhs: RecipesItem, rhs: RecipesItem): Int {
                           if(!lhs.calories.isEmpty() && !rhs.calories.isEmpty())
                                return (lhs.calories.split(" ")[0].toInt().compareTo(rhs.calories.split(" ")[0].toInt()))
                            else return 0
                        }

                    })
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                radiosortfat -> {
                    Collections.sort(recipes, object : Comparator<RecipesItem> {
                        override fun compare(lhs: RecipesItem, rhs: RecipesItem): Int {
                            if(!lhs.fats.isEmpty() && !rhs.fats.isEmpty())
                                return (lhs.fats.split(" ")[0].toInt().compareTo(rhs.fats.split(" ")[0].toInt()))
                            else return 0
                        }

                    })
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
            }
        }

         fun sortByCalories(){

        }
    }
}


