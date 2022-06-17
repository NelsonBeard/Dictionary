package com.geekbrains.dictionary.di

import com.geekbrains.dictionary.data.RetrofitImplementation
import com.geekbrains.dictionary.domain.usecase.TranslationRepo
import com.geekbrains.dictionary.ui.main_screen.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<TranslationRepo> { RetrofitImplementation() }
}

val mainScreen = module {
    viewModel { MainViewModel(translationRepo = get()) }
}