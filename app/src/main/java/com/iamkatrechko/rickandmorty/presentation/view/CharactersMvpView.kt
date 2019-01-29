package com.iamkatrechko.rickandmorty.presentation.view

import android.support.v7.widget.RecyclerView
import com.iamkatrechko.rickandmorty.presentation.base.MvpView

/**
 * View экрана просмотра списка персонажей
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
interface CharactersMvpView : MvpView {

    /** Устанавливает [адаптер][adapter] списка персонажей */
    fun attachCharactersAdapter(adapter: RecyclerView.Adapter<*>)

    /** Останавливает пагинацию */
    fun stopPaging()

    /** Открывает экран просмотра информации о персонаже по его [идентификатору][characterId] */
    fun openCharacterScreen(characterId: Int)
}