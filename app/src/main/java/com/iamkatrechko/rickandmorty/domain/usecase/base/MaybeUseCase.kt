package com.iamkatrechko.rickandmorty.domain.usecase.base

import com.iamkatrechko.rickandmorty.common.extension.TAG
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo

/**
 * Абстрактный Maybe сценарий
 *
 * Для реализации наследника необхоимо переопределить метод buildUseCaseObservable.
 * Schedulers подписки и прослушивания результата должны реализоваться в наследнике
 * @author iamkatrechko
 *         Date: 03.09.18
 *
 * @param [RESULT] результат выполнения
 * @param [PARAMS] параметры сценария
 */
abstract class MaybeUseCase<RESULT, in PARAMS> {

    /** Список подписчиков */
    private val disposables = CompositeDisposable()

    /**
     * Возвращает отложенный сценарий, который реализует всю логику
     * @param [params] параметры сценария
     * @return отложенный сценарий
     */
    protected abstract fun buildUseCaseObservable(params: PARAMS?): Maybe<RESULT>

    /**
     * Выполняет указанный сценарий
     * @param [params]     параметры сценария
     * @param [onSuccess]  слушатель успешного выполнения сценария
     * @param [onError]    слушатель ошибки выполнения сценария
     * @param [onComplete] слушатель завершения выполнения сценария без результата
     */
    fun execute(params: PARAMS? = null, onSuccess: Consumer<RESULT> = Consumer {}, onError: Consumer<Throwable> = Consumer {}, onComplete: Action = Action {}): Disposable =
            observable(params)
                    .subscribe(onSuccess, onError, onComplete)
                    .addTo(disposables)

    /**
     * Возвращает отложенный сценарий
     * @param [params] параметры сценария
     */
    fun observable(params: PARAMS? = null): Maybe<RESULT> =
            buildUseCaseObservable(params)

    /** Останавливает текущую и последующую работу сценария */
    fun dispose() = disposables.dispose()

    /** Останавливает текущую работу сценария */
    fun clear() = disposables.clear()

    /** Выбрасывает ошибку об отсутствии параметров */
    protected fun missingParamError(): Nothing =
            throw IllegalArgumentException("Не заданы параметры сценария $TAG")
}