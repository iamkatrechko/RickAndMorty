package com.iamkatrechko.rickandmorty.common.extension.rx

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/** Переводит выполнение на фоновый поток, а наблюдение на главный */
fun <T> Single<T>.subscribeOnIoToMain(): Single<T> =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())