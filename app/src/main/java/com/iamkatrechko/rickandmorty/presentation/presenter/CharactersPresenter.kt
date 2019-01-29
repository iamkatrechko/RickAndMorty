package com.iamkatrechko.rickandmorty.presentation.presenter

import android.util.Log
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import com.iamkatrechko.rickandmorty.R
import com.iamkatrechko.rickandmorty.common.extension.TAG
import com.iamkatrechko.rickandmorty.common.extension.rx.subscribeOnIoToMain
import com.iamkatrechko.rickandmorty.domain.loader.ImageLoader
import com.iamkatrechko.rickandmorty.domain.repository.CharactersRepository
import com.iamkatrechko.rickandmorty.presentation.base.BasePresenter
import com.iamkatrechko.rickandmorty.presentation.view.CharactersMvpView
import com.iamkatrechko.rickandmorty.ui.adapter.delegate.CharacterDelegateAdapter
import com.iamkatrechko.rickandmorty.ui.adapter.delegate.LoadingDelegateAdapter
import com.iamkatrechko.rickandmorty.ui.adapter.mapper.CharactersAdapterModelMapper
import com.iamkatrechko.rickandmorty.ui.adapter.model.LoadingAdapterModel
import javax.inject.Inject

/**
 * Презентер экрана списка персонажей
 * @author iamkatrechko
 *         Date: 19.01.2019
 *
 * @param charactersRepository репозиторий персонажей
 * @param imageLoader          загрузчик изображений
 */
class CharactersPresenter @Inject constructor(
        private val charactersRepository: CharactersRepository,
        imageLoader: ImageLoader
) : BasePresenter<CharactersMvpView>() {

    /** Список элементов адаптера */
    private val adapterItems = mutableListOf<IComparableItem>()
    /** Текущая страница */
    private var currentPage = 1

    /** Адаптер списка */
    private val delegateAdapter = DiffUtilCompositeAdapter.Builder()
            .add(CharacterDelegateAdapter(imageLoader, ::onCharacterClick))
            .add(LoadingDelegateAdapter())
            .build()

    override fun onAttach(view: CharactersMvpView) {
        super.onAttach(view)
        view.attachCharactersAdapter(delegateAdapter)
        loadCharacters()
    }

    /** Событие необходимости подгрузки персонажей */
    fun onLoadMore() {
        loadCharacters()
    }

    /** Событие нажатия на персонажа */
    private fun onCharacterClick(characterId: Int) {
        view?.openCharacterScreen(characterId)
    }

    /** Загружает очередную страницу персонажей */
    private fun loadCharacters() {
        adapterItems.add(LoadingAdapterModel)
        delegateAdapter.swapData(adapterItems)
        charactersRepository.getCharacters(currentPage)
                .map { it.info to CharactersAdapterModelMapper.map(it.results) }
                .subscribeOnIoToMain()
                .subscribe({ (info, characters) ->
                    if (info.isLastPage) {
                        view?.stopPaging()
                    }
                    currentPage++
                    adapterItems.remove(LoadingAdapterModel)
                    adapterItems.addAll(characters)
                    delegateAdapter.swapData(adapterItems)
                }, {
                    Log.e(TAG, "Ошибка получения персонажей", it)
                    adapterItems.remove(LoadingAdapterModel)
                    delegateAdapter.swapData(adapterItems)
                    view?.showError(R.string.error_load_characters)
                })
                .addToPresenter()
    }
}
