package com.iamkatrechko.rickandmorty.common.extension

/** Тег для логирования */
val Any.TAG: String
    get() {
        return this::class.java.run {
            if (isAnonymousClass) {
                enclosingClass?.simpleName ?: simpleName
            } else {
                simpleName
            }
        }
    }

/** Hack для функции ветвления 'when'. Требует описания всех возможных веток условия, иначе не дает проекту собираться */
fun Any.toUnit() = Unit

/** Функция безопасного приведения объекта к типу [T] */
fun <T> Any?.asGeneric(): T? =
        this as? T
