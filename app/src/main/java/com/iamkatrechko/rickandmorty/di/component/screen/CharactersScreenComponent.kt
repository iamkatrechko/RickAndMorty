package com.iamkatrechko.rickandmorty.di.component.screen

import com.iamkatrechko.rickandmorty.di.module.screen.CharactersScreenModule
import com.iamkatrechko.rickandmorty.ui.CharactersActivity
import dagger.Subcomponent

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
@Subcomponent(modules = [CharactersScreenModule::class])
interface CharactersScreenComponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): CharactersScreenComponent
    }

    fun inject(activity: CharactersActivity)
}