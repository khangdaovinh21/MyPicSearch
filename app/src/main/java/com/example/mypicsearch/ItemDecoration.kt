package com.example.mypicsearch

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

// Item decoration for adding equal spacing around items in a RecyclerView.
class SpacingItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    // Override method to set spacing for each item.
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // Apply spacing evenly around each item
        outRect.apply {
            left = spacing / 2   // Half spacing on the left side
            right = spacing / 2  // Half spacing on the right side
            top = spacing        // Full spacing on the top
            bottom = spacing     // Full spacing on the bottom
        }
    }
}
