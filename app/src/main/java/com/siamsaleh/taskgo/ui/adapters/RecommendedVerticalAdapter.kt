package com.siamsaleh.taskgo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siamsaleh.taskgo.data.model.RecommendedItem
import com.siamsaleh.taskgo.databinding.ItemRecommendedPlaceVerticalBinding
import com.siamsaleh.taskgo.ui.base.BaseViewHolder
import com.siamsaleh.taskgo.ui.listener.OnItemClickListener
import com.siamsaleh.taskgo.util.loadImage

class RecommendedVerticalAdapter(
    private val context: Context,
    private val recommendedList: List<RecommendedItem>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<BaseViewHolder<RecommendedItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RecommendedItem> {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemRecommendedPlaceVerticalBinding.inflate(layoutInflater, parent, false)
        return RecommendedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recommendedList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<RecommendedItem>, position: Int) {
        holder.bind(recommendedList[position], position)
    }

    inner class RecommendedViewHolder(
        private val binding: ItemRecommendedPlaceVerticalBinding
    ) : BaseViewHolder<RecommendedItem>(binding.root) {

        override fun bind(item: RecommendedItem, position: Int) {
            binding.apply {
                recommendedImg.loadImage(context, item.heroImage)
                propertyText.text = item.propertyName
                locationText.text = item.location

                if (position == 0) {
                    bookmarkIcon.visibility = View.VISIBLE
                }
            }

            itemView.setOnClickListener {
                onItemClickListener.onItemClick(item)
            }
        }
    }
}
