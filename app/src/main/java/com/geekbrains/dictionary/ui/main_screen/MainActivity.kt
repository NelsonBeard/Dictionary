package com.geekbrains.dictionary.ui.main_screen

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.dictionary.R
import com.geekbrains.dictionary.data.AdapterRepository
import com.geekbrains.dictionary.data.history.HistoryEntity
import com.geekbrains.dictionary.databinding.ActivityMainBinding
import com.geekbrains.dictionary.ui.history.HistoryFragment
import com.geekbrains.dictionary.ui.word_description.DescriptionFragment
import com.geekbrains.dictionary.utils.BODY
import com.geekbrains.dictionary.utils.HEADER
import com.geekbrains.dictionary.utils.IMAGE_URL
import com.geekbrains.dictionary.utils.NOTE
import com.geekbrains.utils.viewById
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityRetainedScope
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(), AndroidScopeComponent {
    private lateinit var binding: ActivityMainBinding
    private val adapter = DataFromServerListAdapter()

    override val scope: Scope by activityRetainedScope()
    private val vm: MainViewModel by inject()

    private val translationRecyclerView by viewById<RecyclerView>(R.id.translation_recycler_view)
    private val searchWordButton by viewById<Button>(R.id.search_word_button)
    private val inputWordEditText by viewById<EditText>(R.id.input_word_edit_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        translationRecyclerView.adapter = adapter

        restoreAdapter()
        showTranslation()
    }

    private fun restoreAdapter() {
        val savedData = AdapterRepository.getData()

        if (savedData != null) {
            adapter.setData(savedData)
        }
    }

    private fun showTranslation() {
        searchWordButton.setOnClickListener {
            val wordToSearch = inputWordEditText.text.toString()

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                openHistory()
            }
            R.id.menu_search -> {
                showSearchDialog()
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openHistory(): Boolean {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(
                binding.mainContainer.id,
                HistoryFragment.newInstance()
            )
            .commit()
        return true
    }

    private fun showSearchDialog(): Boolean {

        val searchDialogView = LayoutInflater.from(this).inflate(R.layout.search_dialog, null)
        val searchDialog = AlertDialog.Builder(this)
        val searchWordInHistory =
            searchDialogView.findViewById<EditText>(R.id.search_word_in_history_edit_text)
        val showDescription = searchDialogView.findViewById<Button>(R.id.show_description_button)

        searchDialog
            .setView(searchDialogView)
            .create()
            .show()

        showDescription.setOnClickListener {
            val wordForSearch = searchWordInHistory.text.toString()
            vm.apply {
                searchWord(wordForSearch)
                liveDataHistory.observe(this@MainActivity) {
                    if (it == null) {
                        Toast.makeText(this@MainActivity, "Такого слова нет", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        openDescription(it)
                    }
                }
            }
        }
        return true
    }

    private fun openDescription(historyEntity: HistoryEntity) {
        val bundle = Bundle()
        bundle.apply {
            putString(HEADER, historyEntity.text)
            putString(BODY, historyEntity.translation)
            putString(NOTE, historyEntity.note)
            putString(IMAGE_URL, historyEntity.pictureUrl)

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
}
