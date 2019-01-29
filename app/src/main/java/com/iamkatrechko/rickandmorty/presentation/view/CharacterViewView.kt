package com.iamkatrechko.rickandmorty.presentation.view

import android.support.v7.widget.RecyclerView
import com.iamkatrechko.rickandmorty.presentation.base.MvpView

/**
 * View экрана просмотра информации о персонаже
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
interface CharacterViewView : MvpView {

    /** Устанавливает адаптер списка эпизодов */
    fun setEpisodesAdapter(adapter: RecyclerView.Adapter<*>)

    /** Отображает информацию о персонаже */
    fun showCharacter(model: CharacterViewModel)
}

/**
 * View-модель персонажа для отображения на экрана
 * @param id   идентификатор
 * @param name имя
 * ...
 */
data class CharacterViewModel(
        val id: Int,
        val name: String,
        val status: String,
        val species: String,
        val type: String,
        val avatarUrl: String
)