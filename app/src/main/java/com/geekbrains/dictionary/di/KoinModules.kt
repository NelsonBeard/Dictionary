package com.geekbrains.dictionary.di

import androidx.room.Room
import com.geekbrains.dictionary.data.RetrofitImplementation
import com.geekbrains.dictionary.data.RoomImplementation
import com.geekbrains.dictionary.data.history.HistoryDataBase
import com.geekbrains.dictionary.domain.usecase.RepoLocal
import com.geekbrains.dictionary.domain.usecase.TranslationRepo
import com.geekbrains.dictionary.ui.history.HistoryFragment
import com.geekbrains.dictionary.ui.history.HistoryViewModel
import com.geekbrains.dictionary.ui.main_screen.MainActivity
import com.geekbrains.dictionary.ui.main_screen.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<TranslationRepo> { RetrofitImplementation() }
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "history_db").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<RepoLocal> { RoomImplementation(history = get()) }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(translationRepo = get(), repoLocal = get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryFragment>()) {
        viewModel { HistoryViewModel(repoLocal = get()) }
    }
}