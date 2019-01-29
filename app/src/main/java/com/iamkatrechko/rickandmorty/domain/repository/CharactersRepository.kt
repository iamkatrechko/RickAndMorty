package com.iamkatrechko.rickandmorty.domain.repository

import com.iamkatrechko.rickandmorty.data.api.model.Character
import com.iamkatrechko.rickandmorty.data.api.model.CharactersResponse
import io.reactivex.Single

/**
 * Репозиторий персонажей
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
interface CharactersRepository {

    fun getCharacters(page: Int): Single<CharactersResponse>

    fun getCharacter(characterId: Int): Single<Character>
}