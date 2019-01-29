package com.iamkatrechko.rickandmorty.ui.adapter.model

import com.example.delegateadapter.delegate.diff.IComparableItem

/**
 * Модель делегат-адаптера. Виджет загрузки
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
object LoadingAdapterModel : IComparableItem {

    override fun id() = Unit

    override fun content() = Unit
}