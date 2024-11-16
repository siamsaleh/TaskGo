package com.siamsaleh.taskgo.ui.listener

import com.siamsaleh.taskgo.data.model.RecommendedItem

interface OnItemClickListener {
    fun onItemClick(recommendedItem: RecommendedItem)
}