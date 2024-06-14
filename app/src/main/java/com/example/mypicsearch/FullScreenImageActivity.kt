package com.example.mypicsearch

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.example.mypicsearch.databinding.ActivityFullScreenImageBinding

// Activity to display a full-screen image with an option to open its source URL.
class FullScreenImageActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_IMAGE_URL = "imageUrl"      // Key for passing image URL via Intent extras.
        const val EXTRA_SOURCE_URL = "sourceUrl"    // Key for passing source URL via Intent extras.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        val binding = ActivityFullScreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get imageUrl and sourceUrl from intent extras
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        val sourceUrl = intent.getStringExtra(EXTRA_SOURCE_URL)

        // Load the image into ImageView using Glide library
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
            .into(binding.zoomImageView)

        // Set transition name for shared element transition
        ViewCompat.setTransitionName(binding.zoomImageView, "imageTransition")

        // Set OnClickListener for the openSourceButton to open the source URL
        binding.openSourceButton.setOnClickListener {
            openSourcePage(sourceUrl)
        }
    }

    // Function to handle opening the source URL in a web browser
    private fun openSourcePage(sourceUrl: String?) {
        // Check if sourceUrl is not null or blank
        sourceUrl?.let {
            if (it.isNotBlank()) {
                try {
                    // Parse the URL and create an Intent to view it in a browser
                    val webpage = Uri.parse(it)
                    val intent = Intent(Intent.ACTION_VIEW, webpage)
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    // Handle case where no app can handle the URL
                    Toast.makeText(this, "No application found to handle the website", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    // Handle other exceptions that might occur while opening the URL
                    Toast.makeText(this, "Error opening website", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Inform the user that source information is not available
                Toast.makeText(this, "Source information not available", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            // Inform the user that source information is not available if sourceUrl is null
            Toast.makeText(this, "Source information not available", Toast.LENGTH_SHORT).show()
        }
    }
}
