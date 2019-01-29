/*
 * STRICTLY CONFIDENTIAL
 * TRADE SECRET
 * PROPRIETARY:
 *       "BIFIT" JSC, TIN 7719617469
 *       105203, Russia, Moscow, Nizhnyaya Pervomayskaya, 46
 * (c) "BIFIT" JSC, 2018
 *
 * СТРОГО КОНФИДЕНЦИАЛЬНО
 * КОММЕРЧЕСКАЯ ТАЙНА
 * СОБСТВЕННИК:
 *       АО "БИФИТ", ИНН 7719617469
 *       105203, Россия, Москва, ул. Нижняя Первомайская, д. 46
 * (c) АО "БИФИТ", 2018
 */
package com.iamkatrechko.rickandmorty.ui.paging

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Слушатель прокрутки списка с подгрузкой
 * @author iamkatrechko
 *         Date: 19.01.19
 */
class InfiniteScrollListener(
        /** Действие при достижении конца списка */
        private val onEndAction: () -> Unit
) : RecyclerView.OnScrollListener() {

    /** Флаг получения всех страниц данных */
    var isFinish: Boolean = false

    /** Менеджер виджета списка */
    private lateinit var layoutManager: LinearLayoutManager

    /** Флаг состояния загрузки */
    private var loading = true
    /** Количество элементов с конца до начала подгрузки */
    private var visibleThreshold = 2
    /** Позиция 1-го видимого элемента */
    private var firstVisibleItem = 0
    /** Количество видимых элементов */
    private var visibleItemCount = 0
    /** Текущее общее количество элементов */
    private var totalItemCount = 0
    /** Предыдущее общее количество элементов */
    private var previousTotal = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        init(recyclerView)
        if (!isFinish && dy >= 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading) {
                if (totalItemCount > previousTotal + 1) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount) {
                onEndAction()
                loading = true
            }
        }
    }

    /** Предварительная инициализация слушателя */
    private fun init(rv: RecyclerView) {
        if (!::layoutManager.isInitialized) {
            layoutManager = rv.layoutManager as? LinearLayoutManager
                    ?: error("LayoutManager должен наследоваться от LinearLayoutManager")
        }
    }
}