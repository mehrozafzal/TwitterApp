package com.project.twitter.repository.api

import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterSession

class TwitterApiClient(session: TwitterSession?) : TwitterApiClient(session) {
    val customService: ApiService get() = getService(ApiService::class.java)
}

