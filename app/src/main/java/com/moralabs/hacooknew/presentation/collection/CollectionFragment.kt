package com.moralabs.hacooknew.presentation.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class CollectionFragment : Fragment() {

    private lateinit var v : View

    private var collectionViewModel : CollectionViewModel  = get()
    private var _binding : FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    private var collectionList = mutableListOf<Any>()

    private var _filterValue : Boolean = false
    private var _filterItem : String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectionBinding.inflate(inflater,container,false)
        val root : View = binding.root

        _binding?.viewModel = collectionViewModel

        var categoryList : List<Food> =
            listOf(
                Food(title = "fruit"),
                Food(title = "salad"),
                Food(title = "cookie"),
                Food(title = "milk"),
                Food(title = "cream"),)


        binding.mainCollectionRecView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        lifecycleScope.launch {
            collectionViewModel.collectionState.collect {
                when(it){
                    is CollectionUiState.Success -> {
                        collectionList.clear()
                        collectionList.addAll(it.collectionEntity.collectionsHome)

                        binding.mainCollectionRecView.adapter = HomogeneousRecyclerAdapter<ListviewListingCollectionsBinding,
                                Food>(collectionList as List<Food>, R.layout.listview_listing_collections, BR.food){
                            CollectionDialog(requireContext(), it).show()
                        }
                        binding.mainCollectionRecView.adapter?.notifyDataSetChanged()

                    }
                    is CollectionUiState.FilterSuccess -> {

                        binding.mainCollectionRecView.adapter = HomogeneousRecyclerAdapter<ListviewListingCollectionsBinding,
                                Food>(it.listFood, R.layout.listview_listing_collections, BR.food){
                            CollectionDialog(requireContext(), it).show()
                        }

                        binding.mainCollectionRecView.adapter?.notifyDataSetChanged()

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

        binding.categoryRecView.adapter = HomogeneousRecyclerAdapter<FragmentCollectionBinding,
                Food>(categoryList,
            R.layout.category_type_layout, BR.food){
            it.title?.let { title ->
                changeFilter(title)
            }
        }

        collectionViewModel.getLists()
        addListener()
        return root
    }


    private fun changeFilter(search : String){
        if(_filterValue && _filterItem == search){
            collectionViewModel.getLists()
            _filterValue = false
        }
        else{
            collectionViewModel.filterCategory(search)
            _filterValue = true
        }
        _filterItem = search
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
    }
}


