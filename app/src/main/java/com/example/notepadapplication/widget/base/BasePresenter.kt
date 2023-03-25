package com.example.notepadapplication.widget.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<V : MvpView> : ViewModel(), MvpPresenter<V>{

    var view: V? = null

    @CallSuper
    override fun attach(view: V) {
        this.view = view
    }
}