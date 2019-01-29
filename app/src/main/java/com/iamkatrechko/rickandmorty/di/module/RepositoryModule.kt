package com.iamkatrechko.rickandmorty.di.module

import com.iamkatrechko.rickandmorty.data.repository.RickAndMortyCharacterRepository
import com.iamkatrechko.rickandmorty.data.repository.RickAndMortyEpisodeRepository
import com.iamkatrechko.rickandmorty.di.module.RepositoryModule.Binder
import com.iamkatrechko.rickandmorty.domain.repository.CharactersRepository
import com.iamkatrechko.rickandmorty.domain.repository.EpisodesRepository
import dagger.Binds
import dagger.Module

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
@Module(includes = [Binder::class])
class RepositoryModule {

    @Module
    interface Binder {

        @Binds
        fun charactersRepository(r: RickAndMortyCharacterRepository): CharactersRepository

        @Binds
        fun episodesRepository(r: RickAndMortyEpisodeRepository): EpisodesRepository
    }
}