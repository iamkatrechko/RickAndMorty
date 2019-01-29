package com.iamkatrechko.rickandmorty.domain.loader

import android.widget.ImageView

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
interface ImageLoader {

    fun loadInto(imageView: ImageView, imageUrl: String)
}