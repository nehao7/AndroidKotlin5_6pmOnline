package com.o7solutions.androidkotlin5_6pmonline

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var webSettings=binding.webView.settings
        webSettings.javaScriptEnabled=true
        webSettings.setSupportMultipleWindows(true)
        webSettings.setSupportZoom(true)

        binding.webView.webViewClient= WebViewClient()
        binding.webView.loadUrl("https://www.youtube.com/@ghosttogether")
    }
}