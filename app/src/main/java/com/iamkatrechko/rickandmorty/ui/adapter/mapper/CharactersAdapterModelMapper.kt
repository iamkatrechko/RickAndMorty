package com.iamkatrechko.rickandmorty.ui.adapter.mapper

import com.iamkatrechko.rickandmorty.common.mapper.AbstractListMapper
import com.iamkatrechko.rickandmorty.data.api.model.Character
import com.iamkatrechko.rickandmorty.ui.adapter.model.CharacterAdapterModel

object CharactersAdapterModelMapper : AbstractListMapper<Character, CharacterAdapterModel>() {

    override fun map(from: Character): CharacterAdapterModel =
            CharacterAdapterModel(
                    id = from.id,
                    name = from.name,
                    species = from.species,
                    avatarUrl = from.image
            )
}