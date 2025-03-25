package com.o7solutions.androidkotlin5_6pmonline.listpackage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7solutions.androidkotlin5_6pmonline.R
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityListBaseAdapterBinding

class ListBaseAdapterActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBaseAdapterBinding
    lateinit var listBaseAdapter: ListBaseAdapter
    var array= arrayListOf<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityListBaseAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        array.add(Student("Test Name","20","KOTLIN"))
        listBaseAdapter=ListBaseAdapter(array)
        binding.listView.adapter=listBaseAdapter
        listBaseAdapter.notifyDataSetChanged()
    }
}