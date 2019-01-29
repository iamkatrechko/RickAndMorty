package com.iamkatrechko.rickandmorty.ui.adapter.mapper

import com.iamkatrechko.rickandmorty.common.mapper.AbstractListMapper
import com.iamkatrechko.rickandmorty.data.api.model.Episode
import com.iamkatrechko.rickandmorty.ui.adapter.model.EpisodeAdapterModel

object EpisodesMapper : AbstractListMapper<Episode, EpisodeAdapterModel>() {

    override fun map(from: Episode): EpisodeAdapterModel =
            EpisodeAdapterModel(
                    from.id,
                    from.name,
                    from.episode
            )
}