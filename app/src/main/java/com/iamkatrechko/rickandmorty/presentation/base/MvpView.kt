package com.iamkatrechko.rickandmorty.presentation.base

import android.arch.lifecycle.LifecycleOwner
import android.support.annotation.StringRes

interface MvpView : LifecycleOwner {

    /** Отображает/скрывает диалог загрузки по флагу [isDisplayed] */
    fun showLoading(isDisplayed: Boolean)

    /** Отображает пользователю сообщение с текстом [message] */
    fun showMessage(message: String)

    /** Отображает пользователю сообщение по строковому идентификатору [resId] */
    fun showMessage(@StringRes resId: Int)

    /** Отображает пользователю ошибку с текстом [message] */
    fun showError(message: String)

    /** Отображает пользователю ошибку по строковому идентификатору [resId] */
    fun showError(@StringRes resId: Int)
}