package com.iamkatrechko.rickandmorty.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.iamkatrechko.rickandmorty.App
import com.iamkatrechko.rickandmorty.di.component.ScreensComponent
import com.iamkatrechko.rickandmorty.presentation.base.MvpView
import com.iamkatrechko.rickandmorty.ui.util.extension.showToast

/**
 * Базовая активность
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectScreen(App.component.screensComponent())
    }

    /**
     * Производит внедрение зависимостей в активность
     * @param component родительский компонент экранов
     */
    open fun injectScreen(component: ScreensComponent) {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun showLoading(isDisplayed: Boolean) {
    }

    override fun showMessage(message: String) {
        showToast(message)
    }

    override fun showMessage(resId: Int) {
        showToast(resId)
    }

    override fun showError(message: String) {
        showToast(message)
    }

    override fun showError(resId: Int) {
        showToast(resId)
    }
}