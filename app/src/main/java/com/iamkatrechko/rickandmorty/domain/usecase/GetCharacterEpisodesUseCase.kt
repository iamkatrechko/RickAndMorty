package com.iamkatrechko.rickandmorty.domain.usecase

import com.iamkatrechko.rickandmorty.common.extension.rx.subscribeOnIoToMain
import com.iamkatrechko.rickandmorty.data.api.model.Episode
import com.iamkatrechko.rickandmorty.domain.repository.CharactersRepository
import com.iamkatrechko.rickandmorty.domain.repository.EpisodesRepository
import com.iamkatrechko.rickandmorty.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 *
 * @author iamkatrechko
 *         Date: 20.01.2019
 */
class GetCharacterEpisodesUseCase @Inject constructor(
        private val charactersRepository: CharactersRepository,
        private val episodesRepository: EpisodesRepository
) : SingleUseCase<List<Episode>, GetCharacterEpisodesUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params?): Single<List<Episode>> {
        params ?: missingParamError()
        return charactersRepository.getCharacter(params.characterId)
                .map { it.episode.map { url -> url.substringAfterLast("/").toInt() } }
                .zipWith(episodesRepository.getEpisodes(), BiFunction { episodesIds: List<Int>, episodes: List<Episode> ->
                    episodes.filter { episodesIds.contains(it.id) }
                })
                .subscribeOnIoToMain()
    }

    data class Params(
            val characterId: Int
    )
}
