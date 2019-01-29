package com.iamkatrechko.rickandmorty.di.component

import com.iamkatrechko.rickandmorty.di.component.screen.CharacterViewScreenComponent
import com.iamkatrechko.rickandmorty.di.component.screen.CharactersScreenComponent
import dagger.Subcomponent

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
@Subcomponent
interface ScreensComponent {

    fun charactersList(): CharactersScreenComponent.Builder

    fun characterView(): CharacterViewScreenComponent.Builder
}