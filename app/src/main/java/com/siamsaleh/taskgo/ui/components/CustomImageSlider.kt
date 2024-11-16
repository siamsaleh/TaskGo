package com.siamsaleh.taskgo.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2
import com.siamsaleh.taskgo.R
import com.siamsaleh.taskgo.ui.adapters.CustomImageSliderAdapter

class CustomImageSlider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val viewPager: ViewPager2
    private val dotContainer: LinearLayout

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_image_slider, this, true)
        viewPager = findViewById(R.id.viewPager2)
        dotContainer = findViewById(R.id.dotContainer)
    }

    fun setImages(imageUrls: List<String>) {
        // Set up the adapter
        val adapter = CustomImageSliderAdapter(context, imageUrls)
        viewPager.adapter = adapter

        // Create dots
        createDots(imageUrls.size)

        // Update dots on page change
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDots(position, imageUrls.size)
            }
        })
    }

    private fun createDots(count: Int) {
        dotContainer.removeAllViews() // Clear existing dots
        for (i in 0 until count) {
            val dot = ImageView(context)
            dot.setImageResource(R.drawable.inactive_dot) // Inactive dot drawable
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0) // Adjust margin as needed
            dot.layoutParams = params
            dotContainer.addView(dot)
        }
    }

    private fun updateDots(position: Int, count: Int) {
        for (i in 0 until count) {
            val dot = dotContainer.getChildAt(i) as ImageView
            if (i == position) {
                dot.setImageResource(R.drawable.active_dot) // Active dot drawable
            } else {
                dot.setImageResource(R.drawable.inactive_dot) // Inactive dot
            }
        }
    }
}