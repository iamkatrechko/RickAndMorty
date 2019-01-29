package com.iamkatrechko.rickandmorty.di.module

import android.app.Application
import android.content.Context
import com.iamkatrechko.rickandmorty.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
@Module
class AndroidModule {

    @ApplicationScope
    @Provides
    fun ctx(app: Application): Context = app
}