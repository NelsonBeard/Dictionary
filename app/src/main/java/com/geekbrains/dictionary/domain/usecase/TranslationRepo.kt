package com.geekbrains.dictionary.domain.usecase

import com.geekbrains.dictionary.domain.entity.DataFromServer
import io.reactivex.rxjava3.core.Single

interface TranslationRepo {
    fun getTranslation(wordToSearch: String?): Single<List<DataFromServer>>
}