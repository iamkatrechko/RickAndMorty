package com.iamkatrechko.rickandmorty.di

import android.app.Application
import com.iamkatrechko.rickandmorty.App
import com.iamkatrechko.rickandmorty.di.component.ScreensComponent
import com.iamkatrechko.rickandmorty.di.module.AndroidModule
import com.iamkatrechko.rickandmorty.di.module.NetworkModule
import com.iamkatrechko.rickandmorty.di.module.RepositoryModule
import com.iamkatrechko.rickandmorty.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
@ApplicationScope
@Component(
        modules = [
            AndroidModule::class,
            NetworkModule::class,
            RepositoryModule::class
            /* AndroidModule::class,
             NetworkModule::class,
             RepoModule::class,
             DsModule::class,
             ProducerModule::class,
             MapperModule::class,
             CommonSubInteractorsModule::class,
             NavigationScreenModule::class*/
        ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    /** Возвращает родительский компонент экранов */
    fun screensComponent(): ScreensComponent

    fun inject(app: App)
}