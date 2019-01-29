package com.iamkatrechko.rickandmorty.presentation.base

import android.arch.lifecycle.LifecycleObserver

/**
 * Интерфейс базового презентера.
 * В текущей реализации жизненный цикл презентера = жизненному циклу view
 * @author iamkatrechko
 *         Date: 19.01.19
 *
 * @property [V] класс view, которым управляет презентер
 */
interface MvpPresenter<V : MvpView> : LifecycleObserver {

    /** Связывание презентера с view */
    fun onAttach(view: V)

    /** Завершение работы с view */
    fun onDetach()
}