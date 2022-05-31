package com.geekbrains.dictionary.ui

class Presenter : Contract.Presenter {

    private var view: Contract.View? = null

    override fun onAttach(view: Contract.View) {
        this.view = view
    }

    override fun onDetach(view: Contract.View) {
        this.view = null
    }

    override fun requestTranslation(word: String?) {
        view?.showToast(word)
    }
}