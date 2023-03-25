package com.example.notepadapplication.widget.base

interface MvpPresenter<V : MvpView> {
    fun attach(view: V)
}