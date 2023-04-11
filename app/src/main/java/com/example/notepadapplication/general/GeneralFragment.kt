package com.example.notepadapplication.general

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notepadapplication.R
import com.example.notepadapplication.general.adapter.GeneralAdapter
import kotlinx.android.synthetic.main.fragment_general.*

class GeneralFragment : Fragment() {

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
}