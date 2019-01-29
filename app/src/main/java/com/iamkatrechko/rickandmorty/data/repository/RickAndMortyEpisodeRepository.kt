package com.iamkatrechko.rickandmorty.data.repository

import com.iamkatrechko.rickandmorty.data.api.RickAndMortyApi
import com.iamkatrechko.rickandmorty.data.api.model.Episode
import com.iamkatrechko.rickandmorty.di.scope.ApplicationScope
import com.iamkatrechko.rickandmorty.domain.repository.EpisodesRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Репозиторий эпизодов
 * @author iamkatrechko
 *         Date: 19.01.2019
 *
 * @param api API 'Rick And Morty'
 */
@ApplicationScope
class RickAndMortyEpisodeRepository @Inject constructor(
        private val api: RickAndMortyApi
) : EpisodesRepository {

    /** Закешированный список эпизодов */
    private val episodesCache: HashMap<Int, Episode> = hashMapOf()

    override fun getEpisodes(): Single<List<Episode>> {
        if (episodesCache.isNotEmpty()) {
            return Single.just(episodesCache.values.toList())
        }
        // Получаем первую страницу данных, а дальше выкачиваем последующие на основе информации из первой
        return api.getEpisodes(1)
                .flatMap { firstPage ->
                    if (firstPage.info.pages == 1) {
                        // Если данных всего на 1 страницу, отдаем ее
                        return@flatMap Single.just(listOf(firstPage.results))
                    }
                    // Выкачиваем все последующие страницы
                    return@flatMap Observable.range(2, firstPage.info.pages - 1)
                            .concatMapSingle { page ->
                                // Используем concatMapSingle, чтобы все результаты приходили последовательно, а не в разброс
                                api.getEpisodes(page)
                            }
                            .collectInto(mutableListOf(firstPage.results)) { list, response ->
                                // Перекладываем эпизоды нескольких страниц в один список
                                list.add(response.results)
                            }
                }
                .map { it.flatten() } // Вытаскиваем списки из списка наружу (делаем список плоским)
                .doOnSuccess(::putToCache)
    }

    override fun getEpisode(episodeId: Int): Single<Episode> {
        val cached = episodesCache[episodeId]
        if (cached != null) {
            return Single.just(cached)
        }
        return api.getEpisode(episodeId)
    }

    /** Кладем список [эпизодов][episodes] в кеш */
    private fun putToCache(episodes: List<Episode>) {
        episodes.forEach { episode ->
            episodesCache[episode.id] = episode
        }
    }
}