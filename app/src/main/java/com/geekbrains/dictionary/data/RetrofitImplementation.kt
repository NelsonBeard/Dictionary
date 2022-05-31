package com.geekbrains.dictionary.data

import com.geekbrains.dictionary.domain.entity.DataFromServer
import com.geekbrains.dictionary.domain.usecase.TranslationRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation : TranslationRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: SkyEngApi = retrofit.create(SkyEngApi::class.java)

    override fun getTranslation(wordToSearch: String?): Single<List<DataFromServer>> {
        return api.search(wordToSearch)
    }
}