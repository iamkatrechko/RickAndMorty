package com.iamkatrechko.rickandmorty.data.repository

import com.iamkatrechko.rickandmorty.data.api.model.Character
import com.iamkatrechko.rickandmorty.data.api.model.CharactersResponse
import com.iamkatrechko.rickandmorty.data.api.RickAndMortyApi
import com.iamkatrechko.rickandmorty.di.scope.ApplicationScope
import com.iamkatrechko.rickandmorty.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Репозиторий персонажей
 * @author iamkatrechko
 *         Date: 19.01.2019
 *
 * @param api API 'Rick And Morty'
 */
@ApplicationScope
class RickAndMortyCharacterRepository @Inject constructor(
        private val api: RickAndMortyApi
) : CharactersRepository {

    private val cachedCharacters: HashMap<Int, Character> = hashMapOf()

    override fun getCharacters(page: Int): Single<CharactersResponse> {
        return api.getCharacters(page)
                .doOnSuccess { characters ->
                    characters.results.forEach { character ->
                        cachedCharacters[character.id] = character
                    }
                }
    }

    override fun getCharacter(characterId: Int): Single<Character> {
        return cachedCharacters[characterId]?.let { Single.just(it) }
                ?: api.getCharacter(characterId)
    }
}