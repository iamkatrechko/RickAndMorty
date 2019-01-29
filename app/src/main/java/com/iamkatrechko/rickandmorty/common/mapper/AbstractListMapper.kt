package com.iamkatrechko.rickandmorty.common.mapper

import android.os.Looper
import android.util.Log
import com.iamkatrechko.rickandmorty.common.extension.TAG

/**
 * Абстрактный маппер списка сущностей типа [FROM] в тип [TO]
 * @author iamkatrechko
 *         Date: 19.01.19
 *
 * @param FROM тип входных данных
 * @param TO   тип результирующих данных
 */
abstract class AbstractListMapper<FROM, TO> : ListMapper<FROM, TO> {

    override fun map(from: List<FROM>): List<TO> {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w(TAG, "Конвертация данных не должна производиться на UI потоке!")
        }
        return from.map(::map)
    }
}