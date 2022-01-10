package com.moralabs.hacooknew.presentation.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moralabs.hacooknew.R

class CollectionFragment : Fragment() {

    private lateinit var v : View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment








  /*      typeSelectRecView.setHasFixedSize(true)
        typeSelectRecView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        collectionTypesAdapter = CollectionTypeSelectAdapter(typeList, requireContext())
        typeSelectRecView.adapter = collectionTypesAdapter    */

        return inflater.inflate(R.layout.fragment_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
    }
}