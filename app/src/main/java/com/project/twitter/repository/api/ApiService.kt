package com.project.twitter.repository.api

import com.project.twitter.repository.model.favourite.FavouriteResponse
import com.project.twitter.repository.model.retweet.RetweetResponse
import com.project.twitter.repository.model.tweet.TwitterSearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by Mehroz on 29,August,2020
 */

/**
 * Api services to communicate with server
 *
 */
interface ApiService {

    @GET("/1.1/search/tweets.json")
    fun getTweetsInSpecificRadius(
        @Query("q") q: String,
        @Query("geocode") geocode: String,
        @Query("result_type") resultType: String,
        @Query("count") count: Int
    ): Call<TwitterSearchResponse>

    @GET("/1.1/search/tweets.json")
    fun getTweetsByTag(
        @Query("q") q: String
    ): Call<TwitterSearchResponse>

    @POST("/1.1/statuses/retweet/{id}.json")
    fun getRetweetResponse(@Path("id") tweetID: String): Call<RetweetResponse>

    @POST("/1.1/favorites/create.json")
    fun getFavouriteResponse(@Query("id") tweetID: String): Call<FavouriteResponse>

}
