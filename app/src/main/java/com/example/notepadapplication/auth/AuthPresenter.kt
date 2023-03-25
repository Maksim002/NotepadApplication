package com.example.notepadapplication.auth

import com.example.notepadapplication.widget.base.BasePresenter

class AuthPresenter : BasePresenter<AuthContract.View>(), AuthContract.Presenter, AuthContract {

    override fun attach(view: AuthContract.View) {
        super.attach(view)

    }

    override fun showEnabledLady(isLady: Boolean) {
        checkValidation(isLady, false)
    }

    override fun showEnabledSir(isSir: Boolean) {
        checkValidation(false, isSir)
    }

    private fun checkValidation(isLady: Boolean, isSir: Boolean) {
        if (isLady) view?.showEnabledSir(isSir) else if (isSir) view?.showEnabledLady(isLady)
    }
}