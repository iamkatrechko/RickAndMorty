package com.iamkatrechko.rickandmorty.di.module

import com.iamkatrechko.rickandmorty.data.api.ApiCreator
import com.iamkatrechko.rickandmorty.data.api.RickAndMortyApi
import com.iamkatrechko.rickandmorty.data.loader.GlideImageLoader
import com.iamkatrechko.rickandmorty.di.module.NetworkModule.Binder
import com.iamkatrechko.rickandmorty.di.scope.ApplicationScope
import com.iamkatrechko.rickandmorty.domain.loader.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
@Module(includes = [Binder::class])
class NetworkModule {

    @Module
    interface Binder {

        @Binds
        fun imageLoader(loader: GlideImageLoader): ImageLoader
    }

    @ApplicationScope
    @Provides
    fun apiCreator(): ApiCreator =
            ApiCreator()

    @ApplicationScope
    @Provides
    fun api(apiCreator: ApiCreator): RickAndMortyApi =
            apiCreator.createApi()
}