package com.project.twitter.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Mehroz on 29,August,2020
 */

object ConnectivityUtil {

    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}