package com.iamkatrechko.rickandmorty.common.extension.rx

import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/** Переводит выполнение на фоновый поток, а наблюдение на главный */
fun <T> Observable<T>.subscribeOnIoToMain(): Observable<T> =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

/**
 * Производит маппинг значения [Notification.value]
 * @param mapper функция маппинга
 */
fun <T, R> Observable<Notification<T>>.mapValue(mapper: (T) -> R): Observable<Notification<R>> {
    return map {
        when {
            it.isOnComplete -> Notification.createOnComplete()
            it.isOnError -> Notification.createOnError<R>(it.error!!)
            it.isOnNext -> try {
                Notification.createOnNext<R>(mapper(it.value!!))
            } catch (t: Throwable) {
                Notification.createOnError<R>(t)
            }
            else -> error("Неизвестный тип события Notification")
        }
    }
}