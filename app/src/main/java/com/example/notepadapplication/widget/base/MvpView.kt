package com.example.notepadapplication.widget.base

import androidx.annotation.StringRes

interface MvpView {
    fun showErrorMessage(e: Throwable? = null, dismissCallback: (() -> Unit)? = null)
    fun showErrorMessage(@StringRes messageRes: Int)
}