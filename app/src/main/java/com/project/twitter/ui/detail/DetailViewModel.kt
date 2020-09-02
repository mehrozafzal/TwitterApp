package com.project.twitter.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.twitter.repository.api.TwitterApiClient
import com.project.twitter.repository.model.favourite.FavouriteResponse
import com.project.twitter.repository.model.retweet.RetweetResponse
import com.project.twitter.repository.session.UserSession
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterApiException
import com.twitter.sdk.android.core.TwitterException
import javax.inject.Inject


class DetailViewModel @Inject constructor(
) : ViewModel() {

    val retweetResponseLiveData = MutableLiveData<RetweetResponse>()
    val favouriteResponseLiveData = MutableLiveData<FavouriteResponse>()
    val favouriteResponseErrorLiveData = MutableLiveData<String>()
    val retweetResponseErrorLiveData = MutableLiveData<String>()
    fun retweetResponse(tweetID: String) {
        TwitterApiClient(UserSession.session).customService.getRetweetResponse(tweetID)
            .enqueue(object : Callback<RetweetResponse?>() {

                override fun success(result: com.twitter.sdk.android.core.Result<RetweetResponse?>) {
                    if (result.data != null) {
                        retweetResponseLiveData.value = result.data
                    }
                }

                override fun failure(exception: TwitterException) {
                    retweetResponseErrorLiveData.value = (exception as TwitterApiException).errorMessage
                    Log.e("Failed", exception.toString())
                }

            })
    }

    fun favouriteResponse(tweetID: String) {
        TwitterApiClient(UserSession.session).customService.getFavouriteResponse(tweetID)
            .enqueue(object : Callback<FavouriteResponse?>() {

                override fun success(result: com.twitter.sdk.android.core.Result<FavouriteResponse?>) {
                    if (result.data != null) {
                        favouriteResponseLiveData.value = result.data
                    }
                }

                override fun failure(exception: TwitterException) {
                    favouriteResponseErrorLiveData.value = (exception as TwitterApiException).errorMessage
                    Log.e("Failed", exception.toString())
//                    You have already favorited this status.
                }

            })
    }


}