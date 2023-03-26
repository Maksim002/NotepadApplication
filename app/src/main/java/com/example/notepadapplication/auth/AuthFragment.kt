package com.example.notepadapplication.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.notepadapplication.R
import com.example.notepadapplication.widget.base.BaseFragment
import com.example.notepadapplication.widget.model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : BaseFragment<AuthContract.View, AuthContract.Presenter>(), AuthContract.View {

    override val presenter: AuthContract.Presenter = AuthPresenter()

    private var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("User")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomOpen.setOnClickListener {
            dbRef.child(phoneName.text.toString()).setValue(User(firstName.text.toString(), true))
        }
        textLady.setOnCheckedChangeListener { _, isChecked ->
            presenter.showEnabledLady(valid(isChecked))
        }
        textSir.setOnCheckedChangeListener { _, isChecked ->
            presenter.showEnabledSir(valid(isChecked))
        }
        typeText.setOnClickListener { presenter.typeOpen(typeText.text.toString()) }
    }

    override fun showEnabledLady(isLady: Boolean) {
        avatarImageView.setImageDrawable(presenter.getImage("isLady", requireContext()))
        textLady.isChecked = isLady
    }

    override fun showEnabledSir(isSir: Boolean) {
        avatarImageView.setImageDrawable(presenter.getImage("isSir", requireContext()))
        textSir.isChecked = isSir
    }

    override fun showEnabledClear() = avatarImageView.setImageDrawable(null)


    override fun showInputVisibility(isVisibility: Boolean) = with(resources) {
        firstNameLay.isVisible = isVisibility
        layout.isVisible = isVisibility
        avatarImageView.isVisible = isVisibility
        firstNameLay.isVisible = isVisibility
        titleAuth.isVisible = !isVisibility
        if (!isVisibility) textInput(R.string.entrance_text, R.string.authorization_text)
        else textInput(R.string.save_text, R.string.registration_text)
    }

    private fun textInput(open: Int, type: Int){
        bottomOpen.text = getString(open)
        typeText.text = getString(type)
    }

    private fun valid(isCheck: Boolean): Boolean {
        var value = isCheck
        if (!textSir.isChecked && !textLady.isChecked) {
            avatarImageView.setImageDrawable(null)
            value = false
        }
        return value
    }

    override fun showErrorMessage(e: Throwable?, dismissCallback: (() -> Unit)?) {}

    override fun showErrorMessage(messageRes: Int) {}
}