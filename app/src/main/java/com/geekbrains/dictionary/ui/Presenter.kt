package com.geekbrains.dictionary.ui

import com.geekbrains.dictionary.data.RetrofitImplementation
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class Presenter(
    private val retrofitImp: RetrofitImplementation = RetrofitImplementation()
) : Contract.Presenter {

    private var view: Contract.View? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onAttach(view: Contract.View) {
        this.view = view
    }

    override fun onDetach(view: Contract.View) {
        this.view = null
    }

    override fun requestTranslation(word: String?) {

        compositeDisposable.add(
            retrofitImp
                .getTranslation(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { sr ->
                    view?.showTranslation(sr)
                }
        )
    }
}