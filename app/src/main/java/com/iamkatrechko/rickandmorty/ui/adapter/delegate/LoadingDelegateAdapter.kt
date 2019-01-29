package com.iamkatrechko.rickandmorty.ui.adapter.delegate

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.iamkatrechko.rickandmorty.R
import com.iamkatrechko.rickandmorty.ui.adapter.model.LoadingAdapterModel

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
class LoadingDelegateAdapter : KDelegateAdapter<LoadingAdapterModel>() {

    override fun getLayoutId() = R.layout.item_loading

    override fun isForViewType(items: MutableList<*>, pos: Int): Boolean =
            items[pos] is LoadingAdapterModel

    override fun onBind(item: LoadingAdapterModel, viewHolder: KViewHolder) {}
}