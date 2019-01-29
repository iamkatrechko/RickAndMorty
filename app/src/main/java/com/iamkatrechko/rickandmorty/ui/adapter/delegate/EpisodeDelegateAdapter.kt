package com.iamkatrechko.rickandmorty.ui.adapter.delegate

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.iamkatrechko.rickandmorty.R
import com.iamkatrechko.rickandmorty.ui.adapter.model.EpisodeAdapterModel
import kotlinx.android.synthetic.main.item_episode.view.*

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
class EpisodeDelegateAdapter : KDelegateAdapter<EpisodeAdapterModel>() {

    override fun getLayoutId(): Int = R.layout.item_episode

    override fun isForViewType(items: MutableList<*>, pos: Int): Boolean =
            items[pos] is EpisodeAdapterModel

    override fun onBind(item: EpisodeAdapterModel, viewHolder: KViewHolder) {
        viewHolder.itemView.apply {
            tv_title.text = "${item.episode} ${item.name}"
        }
    }
}