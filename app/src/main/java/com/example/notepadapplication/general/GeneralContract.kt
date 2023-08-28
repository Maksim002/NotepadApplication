package com.example.notepadapplication.general

import com.example.notepadapplication.widget.base.MvpPresenter
import com.example.notepadapplication.widget.base.MvpView

interface GeneralContract {
    interface View: MvpView {
        fun showUser(name: String)
    }

    interface Presenter : MvpPresenter<View>{
        fun getListItem(phone: String)
    }
}