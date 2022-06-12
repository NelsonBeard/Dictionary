package com.geekbrains.dictionary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.dictionary.data.LocalRepo
import com.geekbrains.dictionary.domain.entity.DataFromServer
import com.geekbrains.dictionary.domain.usecase.TranslationRepo
import kotlinx.coroutines.launch

class MainViewModel(
    private val translationRepo: TranslationRepo
) : ViewModel() {


    private val _liveDataFromServer = MutableLiveData<List<DataFromServer>>()
    val liveDataFromServer: LiveData<List<DataFromServer>> = _liveDataFromServer

    fun requestTranslation(word: String?) {
        viewModelScope.launch {
            translationRepo.getTranslation(word).apply {
                _liveDataFromServer.postValue(this)
                saveToLocalRepo(this)
            }
        }
    }

    private fun saveToLocalRepo(data: List<DataFromServer>) {
        LocalRepo.saveData(data)
    }

    override fun onCleared() {
        _liveDataFromServer.value = emptyList()
        super.onCleared()
    }
}