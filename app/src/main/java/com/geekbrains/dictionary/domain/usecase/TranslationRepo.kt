package com.geekbrains.dictionary.domain.usecase

import com.geekbrains.dictionary.domain.entity.DataFromServer

interface TranslationRepo {
    suspend fun getTranslation(wordToSearch: String?): List<DataFromServer>
}