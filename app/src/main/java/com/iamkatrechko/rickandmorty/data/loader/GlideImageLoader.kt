package com.iamkatrechko.rickandmorty.data.loader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.iamkatrechko.rickandmorty.domain.loader.ImageLoader
import com.iamkatrechko.rickandmorty.ui.component.GlideApp
import javax.inject.Inject

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 *
 * @param ctx контекст
 */
class GlideImageLoader @Inject constructor(
        ctx: Context
) : ImageLoader {

    private val glide = GlideApp.with(ctx.applicationContext)

    override fun loadInto(imageView: ImageView, imageUrl: String) {
        glide.load(imageUrl)
                .diskCacheStrategy(if (CACHE_ENABLED) DiskCacheStrategy.DATA else DiskCacheStrategy.NONE)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions().circleCrop())
                .into(imageView)
    }

    companion object {

        private const val CACHE_ENABLED = true
    }
}