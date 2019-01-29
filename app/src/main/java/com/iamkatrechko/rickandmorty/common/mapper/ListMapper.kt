package com.iamkatrechko.rickandmorty.common.mapper

/**
 * Маппер списка сущностей типа [FROM] в тип [TO]
 * @author iamkatrechko
 *         Date: 19.01.19
 *
 * @param FROM тип входных данных
 * @param TO   тип результирующих данных
 */
interface ListMapper<FROM, TO> : Mapper<FROM, TO> {

    /** Конвертирует список сущностей из типа [FROM] в тип [TO] */
    fun map(from: List<FROM>): List<TO>
}