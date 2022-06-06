package com.geekbrains.dictionary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.dictionary.data.LocalRepo
import com.geekbrains.dictionary.domain.entity.DataFromServer
import com.geekbrains.dictionary.domain.usecase.TranslationRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainViewModel(
    private val translationRepo: TranslationRepo
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _liveDataFromServer = MutableLiveData<List<DataFromServer>>()
    val liveDataFromServer: LiveData<List<DataFromServer>> = _liveDataFromServer

    fun requestTranslation(word: String?) {
        compositeDisposable.add(
            translationRepo
                .getTranslation(word)
                .subscribeBy {
                    _liveDataFromServer.postValue(it)
                    saveToLocalRepo(it)
                }
        )
    }

    private fun saveToLocalRepo(data: List<DataFromServer>) {
        LocalRepo.saveData(data)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}