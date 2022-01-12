package com.moralabs.hacooknew.presentation.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.moralabs.hacooknew.databinding.*

sealed class HomeAdapterViewHolder(val binding : View) : RecyclerView.ViewHolder(binding) {
    class HomeCardViewHolderTopPick(val cardItem : TopPickLayoutBinding) : HomeAdapterViewHolder(cardItem.root)
    class HomeCardViewHolderLatestCol(val cardItem : LatestCollectionsBinding) : HomeAdapterViewHolder(cardItem.root)
    class HomeCardViewHolderWeeklyTopPick(val cardItem: WeeklyTopPickLayoutBinding) : HomeAdapterViewHolder(cardItem.root)
    class HomeTitleViewHolder(val titleItem : TitlesHomeBinding) : HomeAdapterViewHolder(titleItem.root)
}