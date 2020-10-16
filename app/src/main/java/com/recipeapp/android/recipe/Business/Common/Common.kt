package com.recipeapp.android.recipe.Business.Common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.util.Log


/**
 * Created by DELL on 10/14/2020.
 */
class Common  {
    companion object {
         val URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/"
    }

  public fun verifyAvailableNetwork(activity: AppCompatActivity):Boolean{
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}

