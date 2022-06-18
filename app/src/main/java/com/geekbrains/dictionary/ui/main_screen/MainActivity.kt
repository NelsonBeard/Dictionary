package com.geekbrains.dictionary.ui.main_screen

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.dictionary.R
import com.geekbrains.dictionary.data.AdapterRepository
import com.geekbrains.dictionary.databinding.ActivityMainBinding
import com.geekbrains.dictionary.ui.history.HistoryFragment
import com.geekbrains.dictionary.ui.word_description.DescriptionFragment
import com.geekbrains.dictionary.utils.BODY
import com.geekbrains.dictionary.utils.HEADER
import com.geekbrains.dictionary.utils.IMAGE_URL
import com.geekbrains.dictionary.utils.NOTE
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
        showTranslation()
        openDescriptionFragment()
    }

    private fun restoreAdapter() {
        val savedData = AdapterRepository.getData()

        if (savedData != null) {
            adapter.setData(savedData)
        }
    }

    private fun showTranslation() {
        binding.searchWordButton.setOnClickListener {
            val wordToSearch = binding.inputWordEditText.text.toString()

            observeTranslation(wordToSearch)
            hideKeyboard(currentFocus ?: View(this))
        }
    }

    private fun observeTranslation(wordToSearch: String) {
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

    private fun openDescriptionFragment() {
        adapter.listener = DataFromServerListAdapter.OnItemClick {
            val bundle = Bundle()
            bundle.apply {
                putString(HEADER, it.text)
                putString(BODY, it.meanings?.get(0)?.translation?.text)
                putString(NOTE, it.meanings?.get(0)?.translation?.note)
                putString(IMAGE_URL, it.meanings?.get(0)?.imageUrl)
            }

            supportFragmentManager
                .beginTransaction()
                .addToBackStack("")
                .add(
                    binding.mainContainer.id,
                    DescriptionFragment.newInstance(bundle)
                )
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .add(
                        binding.mainContainer.id,
                        HistoryFragment.newInstance()
                    )
                    .commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}