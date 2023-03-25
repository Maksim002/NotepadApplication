package com.example.notepadapplication.widget.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : MvpView, P : MvpPresenter<V>> : Fragment(), MvpView {

    abstract val presenter: P

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        presenter.attach(this as V)
    }
}