package com.iamkatrechko.rickandmorty.di.scope

import javax.inject.Scope

/**
 * Scope времени жизни всего приложения
 * @author iamkatrechko
 *         Date: 19.01.19
 */
@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope