package com.iamkatrechko.rickandmorty.common.mapper

/**
 * Маппер сущностей из типа [FROM] в тип [TO]
 * @author iamkatrechko
 *         Date: 19.01.19
 *
 * @param FROM тип входных данных
 * @param TO   тип результирующих данных
 */
interface Mapper<FROM, TO> {

    /** Конвертирует сущность из типа [FROM] в тип [TO] */
    fun map(from: FROM): TO
}