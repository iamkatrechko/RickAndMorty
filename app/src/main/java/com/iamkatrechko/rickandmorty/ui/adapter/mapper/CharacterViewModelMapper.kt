package com.iamkatrechko.rickandmorty.ui.adapter.mapper

import com.iamkatrechko.rickandmorty.common.mapper.Mapper
import com.iamkatrechko.rickandmorty.data.api.model.Character
import com.iamkatrechko.rickandmorty.presentation.view.CharacterViewModel

object CharacterViewModelMapper : Mapper<Character, CharacterViewModel> {

    override fun map(from: Character): CharacterViewModel =
            CharacterViewModel(
                    id = from.id,
                    name = from.name,
                    status = from.status,
                    species = from.species,
                    type = from.type,
                    avatarUrl = from.image
            )
}
