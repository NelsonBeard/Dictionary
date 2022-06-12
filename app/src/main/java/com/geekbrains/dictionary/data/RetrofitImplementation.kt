package com.geekbrains.dictionary.data

import com.geekbrains.dictionary.domain.entity.DataFromServer
import com.geekbrains.dictionary.domain.usecase.TranslationRepo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

class RetrofitImplementation : TranslationRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private val api: SkyEngApi = retrofit.create(SkyEngApi::class.java)

    override suspend fun getTranslation(wordToSearch: String?): List<DataFromServer> {
        return api.searchAsync(wordToSearch).await()
    }
}