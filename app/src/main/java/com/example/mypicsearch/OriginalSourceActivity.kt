package com.example.mypicsearch

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

// Activity to display the original source web page in a WebView.
class OriginalSourceActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_URL = "sourceUrl" // Key for passing URL via Intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_original_source) // Set layout for this activity

        val webView: WebView = findViewById(R.id.webView) // Initialize WebView from layout

        val url = intent.getStringExtra(EXTRA_URL) // Get URL from Intent extra

        if (!url.isNullOrEmpty()) {
            webView.apply {
                webViewClient = WebViewClient() // Set a WebViewClient to handle redirects within the WebView
                loadUrl(url) // Load the URL in the WebView
            }
        } else {
            // Handle case where url is null or empty
            finish() // Close activity if URL is not valid
        }
    }
}
