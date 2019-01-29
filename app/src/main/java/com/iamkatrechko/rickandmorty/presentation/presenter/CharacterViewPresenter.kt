package com.iamkatrechko.rickandmorty.presentation.presenter

import android.util.Log
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import com.iamkatrechko.rickandmorty.R
import com.iamkatrechko.rickandmorty.common.extension.TAG
import com.iamkatrechko.rickandmorty.common.extension.rx.subscribeOnIoToMain
import com.iamkatrechko.rickandmorty.domain.repository.CharactersRepository
import com.iamkatrechko.rickandmorty.domain.usecase.GetCharacterEpisodesUseCase
import com.iamkatrechko.rickandmorty.presentation.base.BasePresenter
import com.iamkatrechko.rickandmorty.presentation.view.CharacterViewView
import com.iamkatrechko.rickandmorty.ui.adapter.delegate.EpisodeDelegateAdapter
import com.iamkatrechko.rickandmorty.ui.adapter.delegate.LoadingDelegateAdapter
import com.iamkatrechko.rickandmorty.ui.adapter.mapper.CharacterViewModelMapper
import com.iamkatrechko.rickandmorty.ui.adapter.mapper.EpisodesMapper
import com.iamkatrechko.rickandmorty.ui.adapter.model.LoadingAdapterModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Презентер экрана информации о персонаже
 * @author iamkatrechko
 *         Date: 19.01.2019
 *
 * @param charactersRepository     репозиторий персонажей
 * @param characterEpisodesUseCase сценарий получения списка эпизодов с персонажем
 * @param characterId              идентификатор персонажа
 */
class CharacterViewPresenter @Inject constructor(
        private val charactersRepository: CharactersRepository,
        private val characterEpisodesUseCase: GetCharacterEpisodesUseCase,
        private val characterId: Int
) : BasePresenter<CharacterViewView>() {

    private val episodesAdapter = DiffUtilCompositeAdapter.Builder()
            .add(LoadingDelegateAdapter())
            .add(EpisodeDelegateAdapter())
            .build()

    override fun onAttach(view: CharacterViewView) {
        super.onAttach(view)
        view.setEpisodesAdapter(episodesAdapter)

        loadCharacter()
        loadEpisodes()
    }

    private fun loadCharacter() {
        charactersRepository.getCharacter(characterId)
                .map(CharacterViewModelMapper::map)
                .subscribeOnIoToMain()
                .subscribe({
                    view?.showCharacter(it)
                }, {
                    Log.e(TAG, "Ошибка получения персонажа", it)
                    view?.showError(R.string.error_load_character)
                })
                .addToPresenter()
    }

    private fun loadEpisodes() {
        episodesAdapter.swapData(listOf(LoadingAdapterModel))
        characterEpisodesUseCase.observable(GetCharacterEpisodesUseCase.Params(characterId))
                .map(EpisodesMapper::map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    episodesAdapter.swapData(it)
                }, {
                    Log.e(TAG, "Ошибка получения списка эпизодов", it)
                    view?.showError(R.string.error_load_episodes)
                })
                .addToPresenter()
    }
}
