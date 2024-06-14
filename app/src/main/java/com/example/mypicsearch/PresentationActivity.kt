package com.example.mypicsearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.mypicsearch.models.ImageResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PresentationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation)

        // Get the images JSON from the Intent
        val imagesJson = intent.getStringExtra("imagesJson")
        val gson = Gson()
        val imagesType = object : TypeToken<List<ImageResult>>() {}.type
        val images: List<ImageResult> = gson.fromJson(imagesJson, imagesType)

        // Setup ViewPager2
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = CustomImagePagerAdapter(images)
        viewPager.adapter = adapter
    }
}
