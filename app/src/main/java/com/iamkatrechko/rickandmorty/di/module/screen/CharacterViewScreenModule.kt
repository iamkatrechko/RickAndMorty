package com.iamkatrechko.rickandmorty.di.module.screen

import com.iamkatrechko.rickandmorty.di.module.screen.CharacterViewScreenModule.Binder
import dagger.Module

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
@Module(includes = [Binder::class])
class CharacterViewScreenModule {

    @Module
    interface Binder
}