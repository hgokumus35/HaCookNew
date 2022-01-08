package com.moralabs.hacooknew.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.moralabs.hacooknew.R
import kotlinx.coroutines.flow.collect
import com.moralabs.hacooknew.databinding.FragmentHomeBinding
import com.moralabs.hacooknew.domain.entity.Food
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class HomeFragment : Fragment() {

 /*   private val homeViewModel: HomeViewModel = get()
    private var list = mutableListOf<Any>()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!    */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
/*
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root : View = binding.root

        _binding?.viewModel = homeViewModel

        lifecycleScope.launch {
            homeViewModel.homeState.collect {
                when(it){
                    is HomeUiState.Success -> {
                        it.homeEntity.randomFood?.let { recipe ->
                            list.add("Today's Top Pick")
                            list.add(recipe)
                        }
                        list.addAll(createCollections(it.homeEntity.collections))
                        list.addAll(createRecipe(it.homeEntity.randomFood))
                        binding.homeRecView.adapter = HomeAdapter(list)
                    }
                    is HomeUiState.PageSuccess -> {
                        var beforeCount = list.size
                        var randomList = createRecipeList(it.foodList)
                        list.addAll(randomList)
                        var endCount = list.size
                        binding.homeRecView.adapter?.notifyItemChanged(beforeCount, endCount)
                    }
                }
            }
        }

        homeViewModel.getLists()
        addListener()
        return root  */
    }

    /*
    private fun createRecipe(randomRecipe : List<Food>) : List<Any> {
        var list = mutableListOf<Any>()
        if(randomRecipe.isNotEmpty()){
            list.add("Weekly Top Picks")

            var randomList = mutableListOf<RandomFoodList>()
            randomRecipe.forEachIndexed { index, food ->
                if(index % 2 == 0){
                    randomList.add(RandomFoodList(mutableListOf(food)))
                }else{
                    randomList[randomList.size - 1].list?.add(food)
                }
            }
            list.addAll(randomList)
        }
        return list
    }

    private fun createCollections(collections : List<Food>) : List<Any> {
        var list = mutableListOf<Any>()
        if(collections.isNotEmpty()){
            list.add("Latest Collections")
            list.add(collections)
        }
        return list
    }

    private fun createRecipeList(list : List<Food>) : MutableList<RandomFoodList> {
        var randomList = mutableListOf<RandomFoodList>()

        list.forEachIndexed { index, food ->
            if(index % 2 == 0){
                randomList.add(RandomFoodList(mutableListOf(food)))
            }else{
                randomList[randomList.size - 1].list?.add(food)
            }
        }
        return randomList
    }

    private fun addListener(){
        binding.homeRecView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1))
                    homeViewModel.fetch()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    */

}