package com.iamkatrechko.rickandmorty.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import com.iamkatrechko.rickandmorty.R
import com.iamkatrechko.rickandmorty.di.component.ScreensComponent
import com.iamkatrechko.rickandmorty.presentation.presenter.CharacterViewPresenter
import com.iamkatrechko.rickandmorty.presentation.view.CharacterViewModel
import com.iamkatrechko.rickandmorty.presentation.view.CharacterViewView
import com.iamkatrechko.rickandmorty.ui.base.BaseActivity
import com.iamkatrechko.rickandmorty.ui.component.GlideApp
import com.iamkatrechko.rickandmorty.ui.util.extension.fixEssentialToolbar
import kotlinx.android.synthetic.main.activity_character_view.*
import javax.inject.Inject

/**
 * Активность экрана информации о персонаже
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
class CharacterViewActivity : BaseActivity(), CharacterViewView {

    /** Презентер экрана */
    @Inject lateinit var presenter: CharacterViewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_view)
        setSupportActionBar(toolbar)
        app_bar.fixEssentialToolbar()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.onAttach(this)

        rv_episodes.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun injectScreen(component: ScreensComponent) {
        val characterId = intent.getIntExtra(KEY_CHARACTER_ID, -1)
                .takeIf { it != -1 } ?: error("Не задан обязательный параметр $KEY_CHARACTER_ID")
        component.characterView()
                .characterId(characterId)
                .build()
                .inject(this)
    }

    override fun setEpisodesAdapter(adapter: RecyclerView.Adapter<*>) {
        rv_episodes.adapter = adapter
    }

    override fun showCharacter(model: CharacterViewModel) {
        tv_name.text = model.name
        tv_species.text = model.species
        tv_status.text = model.status
        tv_type.text = model.type
        GlideApp.with(this)
                .load(model.avatarUrl)
                .into(iv_avatar)
    }

    companion object {

        /** Ключ аргумента. Идентификатор персонажа */
        private const val KEY_CHARACTER_ID = "KEY_CHARACTER_ID"

        /**
         * Возвращает интент для запуска активности
         * @param characterId идентификатор персонажа
         */
        fun createIntent(ctx: Context, characterId: Int): Intent {
            return Intent(ctx, CharacterViewActivity::class.java).apply {
                putExtra(KEY_CHARACTER_ID, characterId)
            }
        }
    }
}