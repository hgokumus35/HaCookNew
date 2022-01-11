package com.moralabs.hacooknew.presentation.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moralabs.hacooknew.BR
import com.moralabs.hacooknew.R
import com.moralabs.hacooknew.databinding.FragmentCollectionBinding
import com.moralabs.hacooknew.databinding.GridListingCollectionsBinding
import com.moralabs.hacooknew.databinding.ListviewListingCollectionsBinding
import com.moralabs.hacooknew.domain.entity.Food
import com.moralabs.hacooknew.presentation.common.HomogeneousRecyclerAdapter
import com.moralabs.hacooknew.presentation.home.RandomFoodList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class CollectionFragment : Fragment() {

    private var collectionViewModel : CollectionViewModel  = get()
    private var _binding : FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    private var collectionList = mutableListOf<Any>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectionBinding.inflate(inflater,container,false)
        val root : View = binding.root

        _binding?.viewModel = collectionViewModel

        binding.mainCollectionRecView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        lifecycleScope.launch {
            collectionViewModel.collectionState.collect {
                when(it){
                    is CollectionUiState.Success -> {
                        collectionList.addAll(it.collectionEntity.randomFood)

                        binding.mainCollectionRecView.adapter = HomogeneousRecyclerAdapter<ListviewListingCollectionsBinding,
                                Food>(collectionList as List<Food>, R.layout.listview_listing_collections, BR.food){
                            CollectionDialog(requireContext(), it).show()
                        }
                    }
                }
            }
        }

        binding.gridViewImg.setOnClickListener {
            binding.mainCollectionRecView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.mainCollectionRecView.adapter = HomogeneousRecyclerAdapter<GridListingCollectionsBinding,
                    Food>(collectionList as List<Food>, R.layout.grid_listing_collections, BR.food){
                CollectionDialog(requireContext(), it).show()
            }
        }

        binding.listViewImg.setOnClickListener {
            binding.mainCollectionRecView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            binding.mainCollectionRecView.adapter = HomogeneousRecyclerAdapter<ListviewListingCollectionsBinding,
                    Food>(collectionList as List<Food>, R.layout.listview_listing_collections, BR.food){
                CollectionDialog(requireContext(), it).show()
            }
        }


        binding.fruitButton.setOnClickListener {

        }

        binding.saladButton.setOnClickListener {

        }

        binding.breakfastButton.setOnClickListener {

        }

        binding.dinnerButton.setOnClickListener {

        }

        binding.lunchButton.setOnClickListener {

        }


        collectionViewModel.getLists()
        addListener()
        return root
   //       return inflater.inflate(R.layout.fragment_collection, container, false)
    }

    private fun createCollections(collections : List<Food>) : List<Any> {
        var list = mutableListOf<Any>()
        if(collections.isNotEmpty()){
            list.add(collections)
        }
        return list
    }

    private fun createRecipe(randomRecipe : List<Food>) : List<Any> {
        var list = mutableListOf<Any>()
        if(randomRecipe.isNotEmpty()){
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

    private fun addListener(){
        binding.mainCollectionRecView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1))
                    collectionViewModel.fetch()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


