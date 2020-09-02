package com.project.twitter.di.modules

import com.project.twitter.ui.authenticate.AuthenticationActivity
import com.project.twitter.ui.detail.DetailActivity
import com.project.twitter.ui.map.TwitterMapActivity
import com.project.twitter.ui.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * All your Activities participating in DI would be listed here.
 */
@Module
abstract class ActivityModule {

    /**
     * Marking Activities to be available to contributes for Android Injector
     */
    @ContributesAndroidInjector
    abstract fun contributeAuthenticateActivity(): AuthenticationActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity

    @ContributesAndroidInjector
    abstract fun contributeSearchActivity(): SearchActivity

    @ContributesAndroidInjector
    abstract fun contributeTwitterMapActivity(): TwitterMapActivity
}
