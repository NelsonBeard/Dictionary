package com.geekbrains.dictionary.data

import com.geekbrains.dictionary.data.history.HistoryDao
import com.geekbrains.dictionary.data.history.HistoryEntity
import com.geekbrains.dictionary.domain.entity.DataFromServer
import com.geekbrains.dictionary.domain.usecase.RepoLocal

class RoomImplementation(private val history: HistoryDao) : RepoLocal {
    override suspend fun saveToDb(data: List<DataFromServer>) {
        data.forEach {
            history.insert(
                HistoryEntity(
                    text = it.text.toString(),
                    translation = it.meanings?.get(0)?.translation?.text.toString(),
                    note = it.meanings?.get(0)?.translation?.note.toString(),
                    pictureUrl = it.meanings?.get(0)?.imageUrl.toString()
                )
            )
        }
    }

    override suspend fun getFromDb(): List<HistoryEntity> {
        return history.getAll()
    }

    override suspend fun searchInDb(word: String?): HistoryEntity {
        return history.searchByWord(word)
    }
}