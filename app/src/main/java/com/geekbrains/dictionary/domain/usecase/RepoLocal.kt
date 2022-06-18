package com.geekbrains.dictionary.domain.usecase

import com.geekbrains.dictionary.data.history.HistoryEntity
import com.geekbrains.dictionary.domain.entity.DataFromServer

interface RepoLocal {
    suspend fun saveToDb(data: List<DataFromServer>)
    suspend fun getFromDb(): List<HistoryEntity>
}