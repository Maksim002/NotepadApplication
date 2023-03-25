package com.example.notepadapplication.auth

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.notepadapplication.R
import com.example.notepadapplication.widget.base.BasePresenter

class AuthPresenter : BasePresenter<AuthContract.View>(), AuthContract.Presenter, AuthContract {

    override fun showEnabledLady(isLady: Boolean) = checkValidation(isLady, false)

    override fun showEnabledSir(isSir: Boolean) = checkValidation(false, isSir)

    private fun checkValidation(isLady: Boolean, isSir: Boolean) {
        if (isLady) view?.showEnabledSir(isSir) else if (isSir) view?.showEnabledLady(isLady)
    }

    override fun getImage(type: String, context: Context): Drawable {
        return when (type) {
            "isLady" -> ContextCompat.getDrawable(context, R.drawable.ic_avatar_sir)!!
            else -> ContextCompat.getDrawable(context, R.drawable.ic_avatar)!!
        }
    }
}