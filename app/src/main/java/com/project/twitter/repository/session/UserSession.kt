package com.project.twitter.repository.session

import com.twitter.sdk.android.core.TwitterSession

class UserSession {
    companion object {
        lateinit var session: TwitterSession
        var userID: Long = 0
    }
}