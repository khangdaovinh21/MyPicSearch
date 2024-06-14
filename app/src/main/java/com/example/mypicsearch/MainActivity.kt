package com.example.mypicsearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypicsearch.models.ApiResponse
import com.example.mypicsearch.models.ImageResult
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Main activity for searching and displaying images.
class MainActivity : AppCompatActivity() {

    private lateinit var searchEditText: TextInputEditText // EditText for entering search query
    private lateinit var imageRecyclerView: RecyclerView // RecyclerView for displaying images
    private lateinit var imageAdapter: ImageAdapter // Adapter for binding images to RecyclerView
    private lateinit var searchService: SearchService // Retrofit service interface for image search

    private var images: MutableList<ImageResult> = mutableListOf() // List of ImageResult objects
    private var currentPage = 1 // Current page of image search results
    private var errorCount = 0 // Error count for handling API call failures
    private val apiKey = "6271414cc1cac80f0bf65a012cf0b08a3f4ee28f" // API key for image search
    private val maxErrorCount = 2 // Maximum allowed consecutive errors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        searchEditText = findViewById(R.id.searchEditText)
        imageRecyclerView = findViewById(R.id.imageRecyclerView)
        imageRecyclerView.layoutManager = GridLayoutManager(this, 2) // Set grid layout manager for RecyclerView

        // Initialize adapter and set it to RecyclerView
        imageAdapter = ImageAdapter(images) { imageResult, binding ->
            openFullScreenImage(imageResult, binding.imageView)
        }
        imageRecyclerView.adapter = imageAdapter

        // Initialize Retrofit and SearchService
        val retrofit = RetrofitClient.create()
        searchService = retrofit.create(SearchService::class.java)

        // Setup scroll listener for RecyclerView
        setupScrollListener()

        // Connect onClick event for "Search" Button
        val searchButton = findViewById<Button>(R.id.searchButton)
        searchButton.setOnClickListener {
            currentPage = 1 // Reset current page when performing new search
            errorCount = 0 // Reset error count for new search
            val query = searchEditText.text.toString()
            searchImages(query)
        }

        // Connect onClick event for "Present" Button
        val presentButton = findViewById<Button>(R.id.presentationButton)
        presentButton.setOnClickListener {
            openPresentationActivity()
        }

        // Perform initial search (e.g., searchImages("apple"))
        searchImages("apple")
    }

    // Setup scroll listener to load more images when reaching the end of RecyclerView
    private fun setupScrollListener() {
        imageRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                // Load more images if scrolled to the end
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    currentPage++
                    searchImages(searchEditText.text.toString())
                }
            }
        })
    }

    // Function to search images based on query and current page
    private fun searchImages(query: String) {
        val call = searchService.searchImages(apiKey, query, currentPage)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val newImages = response.body()?.images ?: emptyList()

                    // Clear images list if it's the first page
                    if (currentPage == 1) {
                        images.clear()
                    }
                    images.addAll(newImages)
                    imageAdapter.notifyDataSetChanged()

                    // Show message if no images were returned
                    if (newImages.isEmpty()) {
                        if (errorCount < maxErrorCount) {
                            Toast.makeText(this@MainActivity, "Could not find any more photos of \"$query\"", Toast.LENGTH_SHORT).show()
                            errorCount++
                        }
                    }
                } else {
                    // Show error message if API call fails
                    if (errorCount < maxErrorCount) {
                        Toast.makeText(this@MainActivity, "Failed to load images", Toast.LENGTH_SHORT).show()
                        errorCount++
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Show error message if network request fails
                if (errorCount < maxErrorCount) {
                    Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    errorCount++
                }
            }
        })
    }

    // Function to open full-screen image activity with shared element transition
    private fun openFullScreenImage(imageResult: ImageResult, sharedImageView: ImageView) {
        val intent = Intent(this, FullScreenImageActivity::class.java).apply {
            putExtra("imageUrl", imageResult.imageUrl)
            putExtra("sourceUrl", imageResult.sourceUrl)

            // Pass transition name as extra to FullScreenImageActivity
            putExtra("transitionName", ViewCompat.getTransitionName(sharedImageView))
        }

        // Create the transition animation
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair(sharedImageView, ViewCompat.getTransitionName(sharedImageView))
        )

        // Start the activity with the transition animation
        startActivity(intent, options.toBundle())
    }

    // Function to open presentation activity with all images
    private fun openPresentationActivity() {
        val gson = Gson()
        val imagesJson = gson.toJson(images)
        val intent = Intent(this, PresentationActivity::class.java).apply {
            putExtra("imagesJson", imagesJson)
        }
        startActivity(intent)
    }
}
