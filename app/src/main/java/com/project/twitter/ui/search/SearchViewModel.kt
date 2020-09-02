package com.project.twitter.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.twitter.repository.api.TwitterApiClient
import com.project.twitter.repository.model.tweet.TwitterSearchResponse
import com.project.twitter.repository.session.UserSession
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterException
import javax.inject.Inject


class SearchViewModel @Inject constructor(
) : ViewModel() {

    val searchResponseLiveData = MutableLiveData<TwitterSearchResponse>()

    fun loadTweets(searchString: String) {
        TwitterApiClient(UserSession.session).customService.getTweetsByTag(searchString)
            .enqueue(object : Callback<TwitterSearchResponse?>() {
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

}