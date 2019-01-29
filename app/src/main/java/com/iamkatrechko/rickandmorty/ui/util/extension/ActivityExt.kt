package com.iamkatrechko.rickandmorty.ui.util.extension

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

/** Отображает всплывающее сообщение с текстом [text] */
fun Context.showToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

/** Отображает всплывающее сообщение с текстом по идентификатору ресурса [textResId] */
fun Context.showToast(@StringRes textResId: Int) =
        showToast(getString(textResId))
