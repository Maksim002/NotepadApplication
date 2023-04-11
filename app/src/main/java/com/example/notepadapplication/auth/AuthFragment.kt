package com.example.notepadapplication.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.notepadapplication.R
import com.example.notepadapplication.widget.base.BaseFragment
import com.example.notepadapplication.widget.model.User
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : BaseFragment<AuthContract.View, AuthContract.Presenter>(), AuthContract.View {

    override val presenter: AuthContract.Presenter = AuthPresenter()

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
            presenter.registrationLogo(
                phoneName.text.toString(),
                firstName.text.toString(),
                bottomOpen.text.toString()
            )
        }
        textLady.setOnCheckedChangeListener { _, isChecked ->
            presenter.showEnabledLady(valid(isChecked))
        }
        textSir.setOnCheckedChangeListener { _, isChecked ->
            presenter.showEnabledSir(valid(isChecked))
        }
        phoneName.addTextChangedListener { presenter.showPhone() }
        typeText.setOnClickListener { presenter.typeOpen(typeText.text.toString()) }
        textLady.isChecked = true
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
        thereUser.isVisible = false
        if (!isVisibility) textInput(R.string.entrance_text, R.string.authorization_text)
        else textInput(R.string.save_text, R.string.registration_text)
    }

    override fun showGetWork() {
        findNavController().navigate(R.id.generalFragment)
    }

    override fun showErrorRegistration(messageRes: Int?, visibility: Boolean) {
        if (messageRes != 0) thereUser.text = resources.getString(messageRes!!)
        thereUser.isVisible = visibility
    }

    private fun textInput(open: Int, type: Int) {
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
    override fun showErrorMessage(messageRes: Int) {
        Toast.makeText(requireContext(), resources.getString(messageRes), Toast.LENGTH_SHORT).show()
    }
}