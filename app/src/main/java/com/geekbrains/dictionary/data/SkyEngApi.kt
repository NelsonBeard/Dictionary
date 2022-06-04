package com.geekbrains.dictionary.data

import com.geekbrains.dictionary.domain.entity.DataFromServer
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyEngApi {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String?): Single<List<DataFromServer>>
}