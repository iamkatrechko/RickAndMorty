package com.iamkatrechko.rickandmorty.ui.adapter.model

import com.example.delegateadapter.delegate.diff.IComparableItem

/**
 * Модель делегат-адаптера. Персонаж
 * @author iamkatrechko
 *         Date: 19.01.2019
 *
 * @param id        идентификатор
 * @param name      имя
 * @param species   раса
 * @param avatarUrl ссылка на аватар
 */
data class CharacterAdapterModel(
        val id: Int,
        val name: String,
        val species: String,
        val avatarUrl: String?
) : IComparableItem {

    override fun id() = id

    override fun content() = hashCode()
}