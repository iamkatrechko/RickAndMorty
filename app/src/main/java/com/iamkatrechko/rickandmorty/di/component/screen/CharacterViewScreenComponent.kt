package com.iamkatrechko.rickandmorty.di.component.screen

import com.iamkatrechko.rickandmorty.di.module.screen.CharacterViewScreenModule
import com.iamkatrechko.rickandmorty.ui.CharacterViewActivity
import dagger.BindsInstance
import dagger.Subcomponent

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
@Subcomponent(modules = [CharacterViewScreenModule::class])
interface CharacterViewScreenComponent {

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun characterId(id: Int): Builder

        fun build(): CharacterViewScreenComponent
    }

    fun inject(activity: CharacterViewActivity)
}