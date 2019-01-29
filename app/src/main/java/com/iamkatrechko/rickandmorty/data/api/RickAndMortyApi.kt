package com.iamkatrechko.rickandmorty.data.api

import com.iamkatrechko.rickandmorty.data.api.model.Character
import com.iamkatrechko.rickandmorty.data.api.model.CharactersResponse
import com.iamkatrechko.rickandmorty.data.api.model.Episode
import com.iamkatrechko.rickandmorty.data.api.model.EpisodesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
interface RickAndMortyApi {

    /**
     *
     * @param page номер страницы
     */
    @GET("character")
    fun getCharacters(
            @Query("page") page: Int
    ): Single<CharactersResponse>

    @GET("character/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Int): Single<Character>

    @GET("episode")
    fun getEpisodes(@Query("page") page: Int): Single<EpisodesResponse>

    @GET("episode/{episodeId}")
    fun getEpisode(@Path("episodeId") episodeId: Int): Single<Episode>
}