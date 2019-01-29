package com.iamkatrechko.rickandmorty

import android.app.Application
import com.iamkatrechko.rickandmorty.di.AppComponent
import com.iamkatrechko.rickandmorty.di.DaggerAppComponent

/**
 * Главный класс приложения
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        /** Экземпляр главного класса */
        lateinit var instance: App
            private set

        /** Основной компонент для внедрения зависимостей */
        val component: AppComponent
                by lazy { DaggerAppComponent.builder().application(instance).build() }
    }
}