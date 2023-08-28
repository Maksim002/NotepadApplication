package com.example.notepadapplication.auth

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import com.example.notepadapplication.widget.base.MvpPresenter
import com.example.notepadapplication.widget.base.MvpView
import com.example.notepadapplication.widget.model.OpenType


interface AuthContract {
    interface View: MvpView {
        fun showEnabledLady(isLady: Boolean)
        fun showEnabledSir(isSir: Boolean)
        fun showEnabledClear()
        fun showInputVisibility(isVisibility: Boolean)
        fun showGetWork(phone: String)
        fun showErrorRegistration(@StringRes messageRes: Int? = 0, visibility: Boolean)
    }

    interface Presenter : MvpPresenter<View>{
        fun showEnabledLady(isLady: Boolean)
        fun showEnabledSir(isSir: Boolean)
        fun getImage(type: String, context: Context): Drawable
        fun typeOpen(type: String)
        fun registrationLogo(phone: String, name: String, type: String)
        fun showPhone()
    }
}