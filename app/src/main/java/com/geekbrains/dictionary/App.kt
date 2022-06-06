package com.geekbrains.dictionary

import android.app.Application
import android.content.Context
import com.geekbrains.dictionary.data.RetrofitImplementation
import com.geekbrains.dictionary.domain.usecase.TranslationRepo

class App : Application() {
    val translationRepo: TranslationRepo by lazy { RetrofitImplementation() }
}

val Context.app: App
    get() = applicationContext as App
