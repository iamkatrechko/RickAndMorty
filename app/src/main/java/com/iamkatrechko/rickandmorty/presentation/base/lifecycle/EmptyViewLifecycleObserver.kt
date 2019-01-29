package com.iamkatrechko.rickandmorty.presentation.base.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner

/**
 * Пустая реализация наблюдателя жизненного цикла [Lifecycle] компонента
 * @author iamkatrechko
 *         Date: 19.01.19
 */
class EmptyViewLifecycleObserver : ViewLifecycleObserver {

    override fun onViewAnyEvent(source: LifecycleOwner, event: Lifecycle.Event) {}

    override fun onViewDestroy() {}

    override fun onViewCreate() {}

    override fun onViewPause() {}

    override fun onViewResume() {}

    override fun onViewStart() {}

    override fun onViewStop() {}
}