package com.example.notepadapplication.auth

import com.example.notepadapplication.widget.base.MvpPresenter
import com.example.notepadapplication.widget.base.MvpView


interface AuthContract {
    interface View: MvpView {
        fun showEnabledLady(isLady: Boolean)
        fun showEnabledSir(isSir: Boolean)
        fun showEnabledClear()
    }

    interface Presenter : MvpPresenter<View>{
        fun showEnabledLady(isLady: Boolean)
        fun showEnabledSir(isSir: Boolean)
    }
}