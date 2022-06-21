package com.geekbrains.dictionary.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.dictionary.data.history.HistoryEntity
import com.geekbrains.dictionary.domain.usecase.RepoLocal
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repoLocal: RepoLocal
) : ViewModel() {

    private val _liveData = MutableLiveData<List<String>>()
    val liveData: LiveData<List<String>> = _liveData

    fun getHistory() {
        viewModelScope.launch {
            val headers = getHeaders(repoLocal.getFromDb())
            _liveData.postValue(headers)
        }
    }

    private fun getHeaders(history: List<HistoryEntity>): List<String> {
        val headers = mutableListOf<String>()

        history.forEach {
            headers.add(it.text)
        }
        return headers
    }
}