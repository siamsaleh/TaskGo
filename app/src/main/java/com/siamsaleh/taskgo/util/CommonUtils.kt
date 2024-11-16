package com.siamsaleh.taskgo.util

import android.text.Html
import android.view.animation.OvershootInterpolator
import androidx.core.text.HtmlCompat
import at.blogc.android.views.ExpandableTextView
import java.text.DecimalFormat

object CommonUtils {

    /**
     * This function is used to format the given text and display it in an ExpandableTextView.
     * If the text is longer than the specified length, it will be shortened.
     * @param textView The ExpandableTextView in which the text will be displayed.
     * @param givenText The text to be displayed.
     * @param maxLength The maximum length of the text before it is shortened.
     */
    fun seeMoreText(
        textView: ExpandableTextView,
        givenText: String,
        maxLength: Int = 100
    ) {
        val displayText = if (givenText.length > maxLength) {
            givenText.substring(0, maxLength) + "..."
        } else {
            givenText
        }

        // Set the formatted text to the ExpandableTextView
        textView.text = Html.fromHtml(
            displayText,
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )

        // Set the ExpandableTextView behavior
        textView.expandInterpolator = OvershootInterpolator()
        textView.setAnimationDuration(1000L)

        var isExpand = false
        textView.setOnClickListener {
            if (!isExpand) {
                // Expand the text on click
                textView.maxLines = Integer.MAX_VALUE
                textView.text = givenText
                textView.expand()
                isExpand = true
            }
        }
    }


    /**
     * Formats a nullable Double value with a thousands separator.
     * If the value is null, it returns "0" as the default value.
     *
     * @param value The Double value to format. If null, defaults to "0".
     * @return A string representing the formatted number with a thousands separator (e.g., "1,000").
     */
    fun formatNumberWithThousandsSeparator(value: Double?): String {
        // If the value is null, return an empty string or default value.
        if (value == null) return "0"

        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(value)
    }
}