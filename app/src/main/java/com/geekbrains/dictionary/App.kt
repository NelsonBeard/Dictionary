package com.geekbrains.dictionary

import android.app.Application
import com.geekbrains.dictionary.di.application
import com.geekbrains.dictionary.di.mainScreen
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}
