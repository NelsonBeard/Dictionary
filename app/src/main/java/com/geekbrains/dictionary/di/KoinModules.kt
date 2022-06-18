package com.geekbrains.dictionary.di

import androidx.room.Room
import com.geekbrains.dictionary.data.RetrofitImplementation
import com.geekbrains.dictionary.data.RoomImplementation
import com.geekbrains.dictionary.data.history.HistoryDataBase
import com.geekbrains.dictionary.domain.usecase.RepoLocal
import com.geekbrains.dictionary.domain.usecase.TranslationRepo
import com.geekbrains.dictionary.ui.history.HistoryViewModel
import com.geekbrains.dictionary.ui.main_screen.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<TranslationRepo> { RetrofitImplementation() }
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "history_db").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<RepoLocal> { RoomImplementation(history = get()) }
}

val mainScreen = module {
    viewModel { MainViewModel(translationRepo = get(), repoLocal = get()) }
}

val historyScreen = module{
    viewModel { HistoryViewModel(repoLocal = get()) }
}