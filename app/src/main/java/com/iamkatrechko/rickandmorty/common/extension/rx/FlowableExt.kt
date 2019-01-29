package com.iamkatrechko.rickandmorty.common.extension.rx

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/** Переводит выполнение на фоновый поток, а наблюдение на главный */
fun <T> Flowable<T>.subscribeOnIoToMain(): Flowable<T> =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())