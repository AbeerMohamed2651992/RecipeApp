package com.recipeapp.android.recipe.CustomAdapter

/**
 * Created by DELL on 10/14/2020.
 */
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
//import com.bumptech.glide.Glide
import com.recipeapp.android.recipe.Business.Models.RecipesItem
import com.recipeapp.android.recipe.R
import com.recipeapp.android.recipe.RecipesDetailsActivity
import kotlinx.android.synthetic.main.layout_recipe_item.view.*
import java.util.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.os.AsyncTask
import com.squareup.picasso.Picasso
import java.net.URL


class RecipesAdapter(val recipes: List<RecipesItem>): RecyclerView.Adapter<RecipesViewHolder>(), Filterable {


    var recipesnameFilterList = ArrayList<String>()

    init {
        recipesnameFilterList = getName(recipes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        //attach recipes item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recipe_item, parent, false)

        //when click on row of recycleview it will navigate to details
        return RecipesViewHolder(view).listen { pos, type ->
            val item = recipes.get(pos)

            val list: ArrayList<String> = ArrayList()
            list.add(item.name)
            list.add(item.calories)
            list.add(item.carbos)
            list.add(item.description)
            list.add(item.difficulty.toString())
            list.add(item.fats)
            list.add(item.headline)
            list.add(item.proteins)
            list.add(item.time)

            val intent = Intent(parent.context!!, RecipesDetailsActivity::class.java)
            intent.putStringArrayListExtra("recipedetails",list)
            parent.context.startActivity(intent)
        }
        return RecipesViewHolder(view)
    }



    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        if(position<recipesnameFilterList.count()) {
            holder.itemView.txttitlerecipe.text = recipesnameFilterList[position]

            return holder.bind(recipes[position])
        }
    }

    //get from list of recipes => Array List of String "name"
    fun getName(aList: List<RecipesItem>): ArrayList<String> {
        return aList.map { it.name }.toTypedArray().toCollection(ArrayList())
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    recipesnameFilterList = getName(recipes)
                } else {
                    val resultList = ArrayList<String>()
                    for (row in getName(recipes)) {
                        if (row.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    recipesnameFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = recipesnameFilterList
                return filterResults

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                recipesnameFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }




}

class RecipesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val photo:ImageView = itemView.findViewById(R.id.imgrecipe)
    private val title:TextView = itemView.findViewById(R.id.txttitlerecipe)


    fun bind(recipe: RecipesItem) {
        //load thumb image
        Picasso.get().load(recipe.thumb).into(photo)
        //set name
        title.text = recipe.name
    }

}
