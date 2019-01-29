package com.iamkatrechko.rickandmorty.common.extension.rx

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/** Переводит выполнение на фоновый поток, а наблюдение на главный */
fun Completable.subscribeOnIoToMain(): Completable =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())