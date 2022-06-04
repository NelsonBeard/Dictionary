package com.geekbrains.dictionary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.dictionary.data.RetrofitImplementation
import com.geekbrains.dictionary.domain.entity.DataFromServer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy


class MainViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val translationRepo = RetrofitImplementation()

    private val _liveDataFromServer = MutableLiveData<List<DataFromServer>>()
    val liveDataFromServer: LiveData<List<DataFromServer>> = _liveDataFromServer

    fun requestTranslation(word: String?) {
        compositeDisposable.add(
            translationRepo
                .getTranslation(word)
                .subscribeBy { _liveDataFromServer.postValue(it) }
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}