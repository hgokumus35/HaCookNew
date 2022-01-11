package com.moralabs.hacooknew.util

import android.content.Context
import com.moralabs.hacooknew.domain.entity.Food
import com.moralabs.hacooknew.presentation.collection.CollectionDialog

class Singleton {
    companion object{
        private lateinit var collectionDialog : CollectionDialog

        fun showCollectionDialog(context : Context, food : Food){
            collectionDialog = CollectionDialog(context, food)
            collectionDialog.setCancelable(true)
            collectionDialog.show()
        }
    }
}