package com.o7solutions.androidkotlin5_6pmonline.recycler_package

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.o7solutions.androidkotlin5_6pmonline.R
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityRecyclerBinding
import com.o7solutions.androidkotlin5_6pmonline.listpackage.Student

class RecyclerActivity : AppCompatActivity() {
    lateinit var binding:ActivityRecyclerBinding
    lateinit var recyclerAdapter: RecyclerAdapter
    var array= arrayListOf<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        array.add(Student("Test Name","20","KOTLIN"))
        array.add(Student("Name1","22","C"))
        array.add(Student("New","12","C++"))
        array.add(Student("name45","6","Java"))
        recyclerAdapter= RecyclerAdapter(array)
        binding.rvList.layoutManager=LinearLayoutManager(this)
        binding.rvList.adapter=recyclerAdapter
    }
}