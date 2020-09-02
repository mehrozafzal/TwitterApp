package com.project.twitter.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.twitter.di.base.ViewModelFactory
import com.project.twitter.di.base.ViewModelKey
import com.project.twitter.ui.detail.DetailViewModel
import com.project.twitter.ui.map.MapViewModel
import com.project.twitter.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Mehroz on 29,August,2020
 */

@Module
abstract class ViewModelModule {


    /**
     * Detail View Model
     */
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    /**
     * Search View Model
     */
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    /**
     * Twitter Map View Model
     */
    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(searchViewModel: MapViewModel): ViewModel

    /**
     * Binds ViewModels factory to provide ViewModels.
     */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
