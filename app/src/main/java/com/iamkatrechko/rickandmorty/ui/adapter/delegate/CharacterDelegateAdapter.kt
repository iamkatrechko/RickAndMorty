package com.iamkatrechko.rickandmorty.ui.adapter.delegate

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.iamkatrechko.rickandmorty.R
import com.iamkatrechko.rickandmorty.domain.loader.ImageLoader
import com.iamkatrechko.rickandmorty.ui.adapter.model.CharacterAdapterModel
import kotlinx.android.synthetic.main.item_character.view.*

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
class CharacterDelegateAdapter(
        private val imageLoader: ImageLoader,
        private val onCharacterClick: (characterId: Int) -> Unit
) : KDelegateAdapter<CharacterAdapterModel>() {

    override fun getLayoutId() = R.layout.item_character

    override fun isForViewType(items: MutableList<*>, pos: Int): Boolean =
            items[pos] is CharacterAdapterModel

    override fun onBind(item: CharacterAdapterModel, viewHolder: KViewHolder) {
        viewHolder.apply {
            itemView.tv_name.text = item.name
            itemView.tv_species.text = item.species

            imageLoader.loadInto(itemView.iv_avatar, item.avatarUrl ?: error("Нету url"))

            itemView.setOnClickListener { onCharacterClick.invoke(item.id) }
        }
    }
}