package com.project.twitter.ui.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.twitter.repository.api.TwitterApiClient
import com.project.twitter.repository.model.tweet.TwitterSearchResponse
import com.project.twitter.repository.session.UserSession
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterException

import javax.inject.Inject

class MapViewModel @Inject constructor(
) : ViewModel() {
    val searchResponseLiveData = MutableLiveData<TwitterSearchResponse>()

    fun loadTweets(lat: String, lng: String, distance: String) {
        TwitterApiClient(UserSession.session).customService.getTweetsInSpecificRadius(
            "",
            lat.plus(",").plus(lng).plus(",").plus(distance),
            "recent ",
            100
        ).enqueue(object : Callback<TwitterSearchResponse?>() {
            override fun success(result: com.twitter.sdk.android.core.Result<TwitterSearchResponse?>) {
                if (result.data != null) {
                    searchResponseLiveData.value = result.data
                }
            }

            override fun failure(exception: TwitterException) {
                Log.e("Failed", exception.toString())
                searchResponseLiveData.value = null
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
    }

}