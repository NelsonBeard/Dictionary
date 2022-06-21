package com.geekbrains.dictionary.data

import com.geekbrains.dictionary.domain.entity.DataFromServer

object AdapterRepository {
    private var savedData: List<DataFromServer>? = null

    fun saveData(data: List<DataFromServer>) {
        savedData = data
    }

    fun getData(): List<DataFromServer>? {
        return savedData
    }
}