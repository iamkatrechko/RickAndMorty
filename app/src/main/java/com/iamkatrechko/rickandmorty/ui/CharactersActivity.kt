package com.iamkatrechko.rickandmorty.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.iamkatrechko.rickandmorty.R
import com.iamkatrechko.rickandmorty.di.component.ScreensComponent
import com.iamkatrechko.rickandmorty.presentation.presenter.CharactersPresenter
import com.iamkatrechko.rickandmorty.presentation.view.CharactersMvpView
import com.iamkatrechko.rickandmorty.ui.base.BaseActivity
import com.iamkatrechko.rickandmorty.ui.paging.InfiniteScrollListener
import kotlinx.android.synthetic.main.activity_characters.*
import javax.inject.Inject

class CharactersActivity : BaseActivity(), CharactersMvpView {

    /** Презентер экрана */
    @Inject lateinit var presenter: CharactersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        rv_characters.addOnScrollListener(InfiniteScrollListener(presenter::onLoadMore))
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun injectScreen(component: ScreensComponent) {
        component.charactersList().build().inject(this)
    }

    override fun attachCharactersAdapter(adapter: RecyclerView.Adapter<*>) {
        rv_characters.adapter = adapter
    }

    override fun stopPaging() {
        rv_characters.clearOnScrollListeners()
    }

    override fun openCharacterScreen(characterId: Int) {
        startActivity(CharacterViewActivity.createIntent(this, characterId))
    }
}
