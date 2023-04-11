package com.example.notepadapplication.auth

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.notepadapplication.R
import com.example.notepadapplication.widget.base.BasePresenter
import com.example.notepadapplication.widget.model.OpenType
import com.example.notepadapplication.widget.model.User
import com.google.firebase.database.*

class AuthPresenter : BasePresenter<AuthContract.View>(), AuthContract.Presenter, AuthContract {

    private var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("User")
    override fun showEnabledLady(isLady: Boolean) = checkValidation(isLady, false)
    override fun showEnabledSir(isSir: Boolean) = checkValidation(false, isSir)
    private var phoneUser: ArrayList<String> = arrayListOf()

    override fun attach(view: AuthContract.View) {
        super.attach(view)
        getListUser()
    }

    private fun checkValidation(isLady: Boolean, isSir: Boolean) {
        if (isLady) view?.showEnabledSir(isSir) else if (isSir) view?.showEnabledLady(isLady)
    }

    override fun getImage(type: String, context: Context): Drawable {
        return when (type) {
            "isLady" -> ContextCompat.getDrawable(context, R.drawable.ic_avatar_sir)!!
            else -> ContextCompat.getDrawable(context, R.drawable.ic_avatar)!!
        }
    }

    override fun typeOpen(type: String) {
        when (if (type == OpenType.RE.string) OpenType.AU.string else OpenType.RE.string) {
            OpenType.RE.string -> view?.showInputVisibility(true)
            OpenType.AU.string -> view?.showInputVisibility(false)
        }
    }

    private fun getListUser() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (database in dataSnapshot.children) {
                    if (phoneUser.firstOrNull { database.key.toString() == it } == null)
                        phoneUser.add(database.key.toString())
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    override fun registrationLogo(phone: String, name: String, type: String) {
        if (type == "Open") {
            if (phoneUser.firstOrNull { phone == it } != null) view?.showGetWork()
            else view?.showErrorMessage(R.string.invalid_password)
        } else {
            if (phoneUser.firstOrNull { phone == it } == null) {
                dbRef.child(phone).setValue(User(name, true))
                view?.showInputVisibility(false)
            } else view?.showErrorMessage(R.string.user_exists)
        }
    }
}