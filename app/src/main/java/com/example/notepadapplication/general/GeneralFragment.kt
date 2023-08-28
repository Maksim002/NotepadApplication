package com.example.notepadapplication.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notepadapplication.R
import com.example.notepadapplication.general.adapter.GeneralAdapter
import com.example.notepadapplication.widget.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_general.*

class GeneralFragment : BaseFragment<GeneralContract.View, GeneralContract.Presenter>(), GeneralContract.View  {

    override val presenter: GeneralContract.Presenter = GeneralPresenter()
    private var adapters = GeneralAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backImageView.setOnClickListener { findNavController().popBackStack() }
        presenter.getListItem(requireArguments().getString("phone").toString())
        initAdapter()
    }

    private fun initAdapter() {
        val list: ArrayList<String> = arrayListOf()
        list.add("")
        list.add("")
        list.add("")
        list.add("")
        list.add("")
        list.add("")
        list.add("")
        list.add("")
        list.add("")
        list.add("")
        list.add("")
        list.add("")
        adapters.update(list)
        recyclerView.adapter = adapters
    }

    override fun showUser(name: String) {
        textName.text = name
    }

    override fun showErrorMessage(e: Throwable?, dismissCallback: (() -> Unit)?) {
    }

    override fun showErrorMessage(messageRes: Int) {
    }
}