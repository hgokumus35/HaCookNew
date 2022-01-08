package com.moralabs.hacooknew.presentation.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.moralabs.hacooknew.databinding.LatestCollectionsLayoutBinding
import com.moralabs.hacooknew.databinding.TitlesHomeBinding
import com.moralabs.hacooknew.databinding.TopPickLayoutBinding
import com.moralabs.hacooknew.databinding.WeeklyTopPickLayoutBinding

sealed class HomeAdapterViewHolder(val binding : View) : RecyclerView.ViewHolder(binding) {
    class HomeCardViewHolderTopPick(val cardItem : TopPickLayoutBinding) : HomeAdapterViewHolder(cardItem.root)
    class HomeCardViewHolderLatestCol(val cardItem : LatestCollectionsLayoutBinding) : HomeAdapterViewHolder(cardItem.root)
    class HomeCardViewHolderWeeklyTopPick(val cardItem: WeeklyTopPickLayoutBinding) : HomeAdapterViewHolder(cardItem.root)
    class HomeTitleViewHolder(val titleItem : TitlesHomeBinding) : HomeAdapterViewHolder(titleItem.root)
}