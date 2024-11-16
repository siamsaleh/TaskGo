package com.siamsaleh.taskgo.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siamsaleh.taskgo.data.model.RecommendedItem
import com.siamsaleh.taskgo.databinding.ItemRecommendedPlaceBinding
import com.siamsaleh.taskgo.util.loadImage


class RecommendedAdapter(
    private val context: Context,
    private val recommendedList: List<RecommendedItem>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(recommendedItem: RecommendedItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemRecommendedPlaceBinding.inflate(layoutInflater, parent, false)
        return RecommendedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recommendedList.size
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        holder.bind(recommendedList[position])
    }

    inner class RecommendedViewHolder(
        private val binding: ItemRecommendedPlaceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecommendedItem) {
            binding.apply {
                recommendedImg.loadImage(context, item.heroImage)
                propertyText.text = item.propertyName
                locationText.text = item.location
            }

            itemView.setOnClickListener{
                onItemClickListener.onItemClick(item)
            }
        }
    }
}