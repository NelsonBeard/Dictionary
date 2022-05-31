package com.geekbrains.dictionary.ui

import com.geekbrains.dictionary.domain.entity.DataFromServer

class Contract {
    interface View {
        fun showTranslation(serverResponse: List<DataFromServer>)
        fun showToast(word: String?)
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onDetach(view: View)
        fun requestTranslation(word: String?)
    }
}