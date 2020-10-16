package com.recipeapp.android.recipe.Business.WebServices.RecipesServices

/**
 * Created by DELL on 10/14/2020.
 */
import com.recipeapp.android.recipe.Business.Common.Common
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(Common.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}