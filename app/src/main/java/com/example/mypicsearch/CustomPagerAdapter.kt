package com.example.mypicsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypicsearch.models.ImageResult

// Adapter for displaying a list of images in a ViewPager or RecyclerView.
class CustomImagePagerAdapter(private val images: List<ImageResult>) : RecyclerView.Adapter<CustomImagePagerAdapter.ImageViewHolder>() {

    // Creates a new ViewHolder instance for each item in the RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_image, parent, false)
        return ImageViewHolder(view)
    }

    // Binds data to the ViewHolder, setting the image using Glide library.
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        Glide.with(holder.imageView.context)
            .load(image.imageUrl) // Load image URL into ImageView using Glide.
            .into(holder.imageView)
    }

    // Returns the total number of items in the data set held by the adapter.
    override fun getItemCount(): Int = images.size

    // ViewHolder class for caching views in a RecyclerView item.
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView) // ImageView to display the image.
    }
}
