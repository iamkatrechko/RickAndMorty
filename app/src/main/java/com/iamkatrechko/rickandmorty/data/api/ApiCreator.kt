package com.iamkatrechko.rickandmorty.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * @author iamkatrechko
 *         Date: 19.01.2019
 */
class ApiCreator {

    /** Строитель gson парсера */
    private val gson = GsonBuilder()
            .setLenient()
            .create()

    /** Строитель http клиента */
    private val clientBuilder = OkHttpClient.Builder()

    private val retrofitBuilder = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

    fun createApi(): RickAndMortyApi {
        val retrofit = retrofitBuilder
                .baseUrl(BASE_API_URL)
                .client(clientBuilder.build())
                .build()
        return retrofit.create(RickAndMortyApi::class.java)
    }

    companion object {

        private const val BASE_API_URL = "https://rickandmortyapi.com/api/"
    }
}