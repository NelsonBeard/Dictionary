package com.geekbrains.dictionary.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.dictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = DataFromServerListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.translationRecyclerView.adapter = adapter

        binding.searchWordButton.setOnClickListener {
            val wordToSearch = binding.inputWordEditText.text.toString()

            showTranslation(wordToSearch)
            hideKeyboard(currentFocus ?: View(this))
        }
    }

    private fun showTranslation(wordToSearch: String) {
        viewModel.apply {
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