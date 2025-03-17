package com.o7solutions.androidkotlin5_6pmonline

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityImplicitBinding

class ImplicitActivity : AppCompatActivity() {
    lateinit var binding:ActivityImplicitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityImplicitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnOpenLink.setOnClickListener {
            var intent=Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.svgrepo.com/svg/303108/google-icon-logo"))
            startActivity(intent)
        }

        binding.btnCall.setOnClickListener {
            var intent=Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:7529073222"))
            startActivity(intent)
        }

        //Email , SMS Task
    }
}