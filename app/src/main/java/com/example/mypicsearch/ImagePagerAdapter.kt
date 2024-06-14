package com.example.mypicsearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypicsearch.databinding.ItemImageBinding

// RecyclerView adapter for displaying a list of images specified by URLs.
class ImagePagerAdapter : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    private var imageUrls: List<String> = emptyList() // List of image URLs to display.

    // Function to update the list of image URLs in the adapter.
    fun setImageUrls(urls: List<String>) {
        imageUrls = urls
        notifyDataSetChanged()
    }

    // Inflates the layout for each item in the RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    // Binds data to the ViewHolder, loading image using Glide.
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    // Returns the total number of items in the data set held by the adapter.
    override fun getItemCount(): Int {
        return imageUrls.size
    }

    // ViewHolder class for caching views in a RecyclerView item.
    inner class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        // Function to bind image URL to the ViewHolder.
        fun bind(imageUrl: String) {
            Glide.with(binding.root)
                .load(imageUrl) // Load image URL into ImageView using Glide.
                .placeholder(R.drawable.placeholder_image) // Placeholder image while loading.
                .into(binding.imageView)
        }
    }
}
