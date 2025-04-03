package com.o7solutions.androidkotlin5_6pmonline.recycler_package

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.o7services.androidkotlin_9_11am.recycler_package.NotesDatabase
import com.o7services.androidkotlin_9_11am.recycler_package.NotesEntity
import com.o7solutions.androidkotlin5_6pmonline.R
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityRecyclerBinding
import com.o7solutions.androidkotlin5_6pmonline.listpackage.Student

class RecyclerActivity : AppCompatActivity(), RecyclerAdapter.OnClick{
    lateinit var binding:ActivityRecyclerBinding
    lateinit var recyclerAdapter: RecyclerAdapter
    var Noteslist= arrayListOf<NotesEntity>()
    lateinit var notesDatabase: NotesDatabase

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
        notesDatabase=NotesDatabase.getInstance(this)

        notesDatabase.notesInterface().insertTodo(NotesEntity(title = "test New", description = "C++"))
        array.add(Student("Test Name","20","KOTLIN"))
        array.add(Student("Name1","22","C"))
        array.add(Student("New","12","C++"))
        array.add(Student("name45","6","Java"))
        recyclerAdapter= RecyclerAdapter(array,this)
        binding.rvList.layoutManager=LinearLayoutManager(this)
        binding.rvList.adapter=recyclerAdapter
    }

    override fun update(position: Int) {
        Toast.makeText(this,"Update Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun delete(position: Int) {
        Toast.makeText(this,"Delete Clicked", Toast.LENGTH_SHORT).show()
        notesDatabase.notesInterface().deleteTodoEntity(Noteslist[position])
        getList()
        recyclerAdapter.notifyDataSetChanged()

    }
    private fun getList(){
        Noteslist.clear()
        Noteslist.addAll(notesDatabase.notesInterface().getList())
    }
}