package com.o7solutions.androidkotlin5_6pmonline

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityListViewBinding

class ListViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityListViewBinding
    var list= arrayListOf("One", "Two" ,"Three")
    lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        binding.listView.adapter=arrayAdapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
//            Toast.makeText(this@ListViewActivity,"$position", Toast.LENGTH_SHORT).show()
            Toast.makeText(this@ListViewActivity,"${parent.getItemAtPosition(position)}", Toast.LENGTH_SHORT).show()
        }

        binding.listView.setOnItemLongClickListener { parent, view, position, id ->
            Toast.makeText(this@ListViewActivity,"$id", Toast.LENGTH_SHORT).show()

            return@setOnItemLongClickListener true
        }


    }
}