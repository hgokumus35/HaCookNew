package com.moralabs.hacooknew.presentation.collection

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CollectionAdapter(private val collectionList : List<Any>) : RecyclerView.Adapter<CollectionAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionAdapterViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CollectionAdapterViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}