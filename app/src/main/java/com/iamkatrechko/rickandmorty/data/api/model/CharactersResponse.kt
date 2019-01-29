package com.iamkatrechko.rickandmorty.data.api.model

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
data class CharactersResponse(
        val info: Info,
        val results: List<Character>
)

data class Info(
        val count: Int,
        val pages: Int,
        val next: String,
        val prev: String
) {

    val isLastPage: Boolean
        get() = next.isEmpty()
}
