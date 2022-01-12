package com.moralabs.hacooknew.presentation.home

import android.view.LayoutInflater
import com.moralabs.hacooknew.BR
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moralabs.hacooknew.R
import com.moralabs.hacooknew.databinding.*
import com.moralabs.hacooknew.domain.entity.Food
import com.moralabs.hacooknew.presentation.common.HomogeneousRecyclerAdapter

class HomeAdapter(private val homeList : List<Any>, private var itemCLickListener : itemClickListener) : RecyclerView.Adapter<HomeAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        when(viewType){
            R.layout.top_pick_layout -> {
                val binding = TopPickLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HomeAdapterViewHolder.HomeCardViewHolderTopPick(binding)
            }
            R.layout.latest_collections -> {
                val binding = LatestCollectionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HomeAdapterViewHolder.HomeCardViewHolderLatestCol(binding)
            }
            R.layout.weekly_top_pick_layout -> {
                val binding = WeeklyTopPickLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HomeAdapterViewHolder.HomeCardViewHolderWeeklyTopPick(binding)
            }
            R.layout.titles_home -> {
                val binding = TitlesHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HomeAdapterViewHolder.HomeTitleViewHolder(binding)
            }
        }
        val binding = TitlesHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeAdapterViewHolder.HomeTitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        when(holder) {
            is HomeAdapterViewHolder.HomeCardViewHolderTopPick -> {
                holder.cardItem.food = homeList[position] as? Food
            }
            is HomeAdapterViewHolder.HomeCardViewHolderLatestCol -> {
                holder.cardItem.latestCollRecView.adapter = HomogeneousRecyclerAdapter<LatestCollectionsLayoutBinding,
                        Food>(homeList[position] as List<Food>,
                        R.layout.latest_collections_layout, BR.food)
            }
            is HomeAdapterViewHolder.HomeCardViewHolderWeeklyTopPick -> {
                val list = (homeList[position] as RandomFoodList).list
                holder.cardItem.leftFood = if(list?.size ?: 0 > 0) list?.get(0) else null
                holder.cardItem.rightFood = if(list?.size ?: 0 > 1) list?.get(1) else null

                holder.cardItem.leftButton.setOnClickListener {
                    itemCLickListener.onItemSelected(
                        holder.cardItem.leftFood
                    )
                }

                holder.cardItem.rightButton.setOnClickListener {
                    itemCLickListener.onItemSelected(
                        holder.cardItem.rightFood
                    )
                }
            }
            is HomeAdapterViewHolder.HomeTitleViewHolder -> {
                holder.titleItem.titleHomeText.text = homeList[position] as? String
            }
        }
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    override fun getItemViewType(position: Int): Int {
        when(homeList[position]){
            is String -> return R.layout.titles_home
            is Food -> return R.layout.top_pick_layout
            is List<*> -> return R.layout.latest_collections
            is RandomFoodList -> return R.layout.weekly_top_pick_layout
        }
        return R.layout.titles_home
    }
}

interface itemClickListener{
    fun onItemSelected(foodItem : Any?)
}