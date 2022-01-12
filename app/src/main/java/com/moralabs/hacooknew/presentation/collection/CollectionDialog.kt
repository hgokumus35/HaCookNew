package com.moralabs.hacooknew.presentation.collection

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.core.text.parseAsHtml
import com.bumptech.glide.Glide
import com.moralabs.hacooknew.databinding.DialogLayoutBinding
import com.moralabs.hacooknew.domain.entity.Food

class CollectionDialog(context : Context, val food: Food) : Dialog(context) {

    private lateinit var binding : DialogLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(true)

        binding.dialogTitleText.text = food.title
        binding.dialogMinuteText.text = food.readyInMinutes.toString()
        binding.dialogDescText.text = food.summary!!.parseAsHtml()

        if(!food.image.isNullOrBlank()){
            Glide.with(context)
                .load(food.image)
                .into(binding.dialogImgView)
        }

    }
}