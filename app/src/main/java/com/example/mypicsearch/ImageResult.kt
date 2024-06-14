package com.example.mypicsearch

import com.google.gson.annotations.SerializedName

// Data class representing an image result.
data class ImageResult(
    @SerializedName("url") val imageUrl: String,        // URL of the image.
    @SerializedName("sourceUrl") val sourceUrl: String  // URL of the source where the image is from.
)
