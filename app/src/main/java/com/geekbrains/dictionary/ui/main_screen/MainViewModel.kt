package com.geekbrains.dictionary.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.dictionary.data.AdapterRepository
import com.geekbrains.dictionary.data.history.HistoryEntity
import com.geekbrains.dictionary.domain.entity.DataFromServer
import com.geekbrains.dictionary.domain.usecase.RepoLocal
import com.geekbrains.dictionary.domain.usecase.TranslationRepo
import kotlinx.coroutines.launch

class MainViewModel(
    private val translationRepo: TranslationRepo,
    private val repoLocal: RepoLocal,
) : ViewModel() {

    private val _liveDataFromServer = MutableLiveData<List<DataFromServer>>()
    val liveDataFromServer: LiveData<List<DataFromServer>> = _liveDataFromServer

    private val _liveDataHistory = MutableLiveData<HistoryEntity?>()
    val liveDataHistory: MutableLiveData<HistoryEntity?> = _liveDataHistory

    fun requestTranslation(word: String?) {
        viewModelScope.launch {
            translationRepo.getTranslation(word).apply {
                _liveDataFromServer.postValue(this)
                repoLocal.saveToDb(this)
                saveAdapter(this)
            }
        }
    }

    fun searchWord(word: String?) {
        viewModelScope.launch {
            repoLocal.searchInDb(word).apply {
                _liveDataHistory.postValue(this)
            }
        }
    }

    private fun saveAdapter(data: List<DataFromServer>) {
        AdapterRepository.saveData(data)
    }

    override fun onCleared() {
        _liveDataFromServer.value = emptyList()
        _liveDataHistory.value = null
        super.onCleared()
    }
}

