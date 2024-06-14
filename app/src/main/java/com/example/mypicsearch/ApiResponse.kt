package com.example.mypicsearch.models

import com.google.gson.annotations.SerializedName

data class ImageResult(
    // Represents an image result with its URL and source URL.
    @SerializedName("imageUrl") val imageUrl: String, // URL of the image.
    @SerializedName("sourceUrl") val sourceUrl: String // URL of the source where the image is from.
)

data class ApiResponse(
    // Represents a response containing a list of ImageResult objects.
    @SerializedName("images") val images: List<ImageResult> // List of ImageResult objects.
)
