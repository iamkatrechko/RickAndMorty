package com.iamkatrechko.rickandmorty.ui.util.extension

import android.view.View

/** Задает высоту в px */
fun View.setHeight(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = value
        layoutParams = lp
    }
}

/** Задает ширину в px */
fun View.setWidth(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = value
        layoutParams = lp
    }
}