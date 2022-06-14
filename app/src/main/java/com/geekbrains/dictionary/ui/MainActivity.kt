package com.geekbrains.dictionary.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.dictionary.data.LocalRepo
import com.geekbrains.dictionary.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = DataFromServerListAdapter()

    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.translationRecyclerView.adapter = adapter
        restoreAdapter()

        binding.searchWordButton.setOnClickListener {
            val wordToSearch = binding.inputWordEditText.text.toString()

            showTranslation(wordToSearch)
            hideKeyboard(currentFocus ?: View(this))
        }
    }

    private fun restoreAdapter() {
        val savedData = LocalRepo.getData()

        if (savedData != null) {
            adapter.setData(savedData)
        }
    }

    private fun showTranslation(wordToSearch: String) {
        vm.apply {
            requestTranslation(wordToSearch)
            liveDataFromServer.observe(this@MainActivity) {
                adapter.setData(it)
            }
        }
    }

    private fun hideKeyboard(view: View) {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(view.windowToken, 0)
    }
}