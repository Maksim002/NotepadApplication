package com.example.notepadapplication.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.notepadapplication.R
import com.example.notepadapplication.widget.base.BaseFragment
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
        bottomOpen.setOnClickListener { findNavController().navigate(R.id.generalFragment) }
        textLady.setOnCheckedChangeListener { _, isChecked -> presenter.showEnabledLady(valid(isChecked)) }
        textSir.setOnCheckedChangeListener { _, isChecked -> presenter.showEnabledSir(valid(isChecked)) }
    }

    override fun showEnabledLady(isLady: Boolean) {
        avatarImageView.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_avatar_sir
            )
        )
        textLady.isChecked = isLady
    }

    override fun showEnabledSir(isSir: Boolean) {
        avatarImageView.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_avatar
            )
        )
        textSir.isChecked = isSir
    }

    override fun showEnabledClear() {
        avatarImageView.setImageDrawable(null)
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