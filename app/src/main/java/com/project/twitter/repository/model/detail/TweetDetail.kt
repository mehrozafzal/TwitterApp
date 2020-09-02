package com.project.twitter.repository.model.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TweetDetail(
    val twitterHandle: String,
    val tweetText: String?,
    val tweetTimeStamp: String?,
    val userAvatar: String?,
    val tweetID:String
) : Parcelable