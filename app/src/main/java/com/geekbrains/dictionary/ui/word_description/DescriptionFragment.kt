package com.geekbrains.dictionary.ui.word_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.geekbrains.dictionary.R
import com.geekbrains.dictionary.databinding.FragmentDescriptionBinding
import com.geekbrains.dictionary.util.*

class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescriptionBinding.bind(view)

        getDescription()
    }

    private fun getDescription() {
        val imageUrl = IMAGE_URL_PREFIX + arguments?.getString(IMAGE_URL)

        binding.translationHeaderTextView.text = arguments?.getString(HEADER)
        binding.translationBodyTextView.text = arguments?.getString(BODY)
        binding.translationNoteTextView.text = arguments?.getString(NOTE)
        binding.translationImageView.load(imageUrl) {
            transformations(CircleCropTransformation())
        }
    }

    companion object {
        fun newInstance(bundle: Bundle): DescriptionFragment {
            val fragment = DescriptionFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}