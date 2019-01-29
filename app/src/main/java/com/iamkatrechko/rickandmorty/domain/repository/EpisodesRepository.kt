package com.iamkatrechko.rickandmorty.domain.repository

import com.iamkatrechko.rickandmorty.data.api.model.Episode
import io.reactivex.Single

/**
 * Репозиторий эпизодов
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
interface EpisodesRepository {

    fun getEpisodes(): Single<List<Episode>>

    fun getEpisode(episodeId: Int): Single<Episode>
}