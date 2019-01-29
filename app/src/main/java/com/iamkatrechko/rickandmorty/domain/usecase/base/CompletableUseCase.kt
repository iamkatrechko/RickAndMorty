package com.iamkatrechko.rickandmorty.domain.usecase.base

import com.iamkatrechko.rickandmorty.common.extension.TAG
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo

/**
 * Абстрактный Completable сценарий
 *
 * Для реализации наследника необходимо переопределить метод buildUseCaseObservable.
 * Schedulers подписки и прослушивания результата реализуются в наследнике
 * @author iamkatrechko
 *         Date: 19.01.19
 *
 * @property [PARAMS] параметры сценария
 */
abstract class CompletableUseCase<in PARAMS> {

    /** Список подписчиков */
    private val disposables = CompositeDisposable()

    /**
     * Возвращает отложенный сценарий, который реализует всю логику
     * @param [params] параметры сценария
     * @return отложенный сценарий выполнения
     */
    protected abstract fun buildUseCaseObservable(params: PARAMS?): Completable

    /**
     * Выполняет указанный сценарий
     * @param [params]    параметры сценария
     * @param [onSuccess] действие при успешном выполнении сценария
     * @param [onError]   слушатель ошибки выполнения сценария
     */
    fun execute(params: PARAMS? = null, onSuccess: Action = Action {}, onError: Consumer<Throwable> = Consumer {}): Disposable =
            observable(params)
                    .subscribe(onSuccess, onError)
                    .addTo(disposables)

    /**
     * Возвращает отложенный сценарий
     * @param [params] параметры сценария
     */
    fun observable(params: PARAMS? = null): Completable =
            buildUseCaseObservable(params)

    /** Останавливает текущую и последующую работу сценария */
    fun dispose() = disposables.dispose()

    /** Останавливает текущую работу сценария */
    fun clear() = disposables.clear()

    /** Выбрасывает ошибку об отсутствии параметров */
    protected fun missingParamError(): Nothing =
            throw IllegalArgumentException("Не заданы параметры сценария $TAG")
}