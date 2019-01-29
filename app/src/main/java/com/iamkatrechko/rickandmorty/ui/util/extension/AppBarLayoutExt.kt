package com.iamkatrechko.rickandmorty.ui.util.extension

import android.support.design.widget.AppBarLayout
import android.widget.FrameLayout

/** Исправляет высоту toolbar на Essential Phone и других телефонах с челкой */
fun AppBarLayout.fixEssentialToolbar() {
    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    val parameter = this.layoutParams as FrameLayout.LayoutParams
    parameter.setMargins(parameter.leftMargin, getStatusBarHeight(), parameter.rightMargin, parameter.bottomMargin)
    this.layoutParams = parameter
}