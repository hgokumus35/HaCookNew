package com.moralabs.hacooknew.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindableUtil {
    companion object{
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view : ImageView, imageUrl : String?){
            view.setImageResource(0)
            Glide.with(view.context).clear(view)

            if(!imageUrl.isNullOrBlank()){
                Glide.with(view.context)
                    .load(imageUrl)
                    .into(view)
            }
        }

        @BindingAdapter("intToString")
        @JvmStatic
        fun intToString(view : TextView, intToString : Int?){
            view.text = intToString.toString()
        }
    }
}