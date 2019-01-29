package com.iamkatrechko.rickandmorty.ui.adapter.model

import com.example.delegateadapter.delegate.diff.IComparableItem

/**
 * Модель делегат-адаптера. Эпизод
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
data class EpisodeAdapterModel(
        val id: Int,
        val name: String,
        val episode: String
) : IComparableItem {

    override fun id() = id

    override fun content() = hashCode()
}