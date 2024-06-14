package com.example.mypicsearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypicsearch.databinding.ItemImageBinding
import com.example.mypicsearch.models.ImageResult

// RecyclerView adapter for displaying a list of images.
class ImageAdapter(
    private var images: MutableList<ImageResult>,  // List of ImageResult objects to display.
    private val onItemClick: (ImageResult, ItemImageBinding) -> Unit // Callback to handle item click events.
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    // Function to update the list of images in the adapter.
    fun updateImages(newImages: List<ImageResult>) {
        images.clear()
        images.addAll(newImages)
        notifyDataSetChanged()
    }

    // Inflates the layout for each item in the RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    // Binds data to the ViewHolder, setting up click listener and loading image using Glide.
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    // Returns the total number of items in the data set held by the adapter.
    override fun getItemCount(): Int = images.size

    // ViewHolder class for caching views in a RecyclerView item.
    inner class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        // Initialize the ViewHolder, setting up click listener for item clicks.
        init {
            binding.root.setOnClickListener {
                onItemClick.invoke(images[adapterPosition], binding)
            }
        }

        // Function to bind data to the ViewHolder.
        fun bind(image: ImageResult) {
            Glide.with(binding.root)
                .load(image.imageUrl) // Load image URL into ImageView using Glide.
                .placeholder(R.drawable.placeholder_image) // Placeholder image while loading.
                .into(binding.imageView)

            // Set transition name for shared element transition
            binding.imageView.transitionName = "imageTransition_${adapterPosition}"
        }
    }
}
