package com.example.notepadapplication.general.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notepadapplication.R
import com.example.notepadapplication.widget.adapter.GenericRecyclerAdapter
import com.example.notepadapplication.widget.adapter.ViewHolder

class GeneralAdapter(var item: ArrayList<String> = arrayListOf()): GenericRecyclerAdapter<String>(item) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_general_fragment)
    }

    override fun bind(item: String, holder: ViewHolder) {

    }
}