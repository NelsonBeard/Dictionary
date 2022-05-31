package com.geekbrains.dictionary.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.dictionary.databinding.ActivityMainBinding
import com.geekbrains.dictionary.domain.entity.DataFromServer

class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var binding: ActivityMainBinding
    private val presenter = Presenter()
    private var wordToSearch: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onAttach(this)

        binding.searchWordButton.setOnClickListener {
            wordToSearch = binding.inputWordEditText.text.toString()
            presenter.requestTranslation(wordToSearch)
        }
    }

    override fun showTranslation(serverResponse: List<DataFromServer>) {
        TODO("Not yet implemented")
    }

    override fun showToast(word:String?) {
        Toast.makeText(this, word, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.onDetach(this)
        super.onDestroy()
    }
}