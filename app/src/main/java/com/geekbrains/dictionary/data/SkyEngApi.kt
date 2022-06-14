package com.geekbrains.dictionary.data

import com.geekbrains.dictionary.domain.entity.DataFromServer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyEngApi {
    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String?): Deferred<List<DataFromServer>>
}