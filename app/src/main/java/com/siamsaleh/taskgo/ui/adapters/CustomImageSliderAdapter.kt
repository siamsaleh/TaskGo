package com.siamsaleh.taskgo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.imageview.ShapeableImageView
import com.siamsaleh.taskgo.R

class CustomImageSliderAdapter(
    private val context: Context,
    private val imageList: List<String?>? // List of image URLs or drawable resources
) : RecyclerView.Adapter<CustomImageSliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ShapeableImageView = itemView.findViewById(R.id.shapeableImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_custom_image_slider, parent, false)
        return SliderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val imageUrl = imageList?.get(position)

        // Load the image into ShapeableImageView using Glide
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.gradient_bg)
            .error(R.drawable.gradient_bg)
            .transform(RoundedCorners(30)) // Apply rounded corners to each image
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return imageList?.size ?: 0
    }
}

