package com.o7solutions.androidkotlin5_6pmonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var button:Button?=null
    var editText:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        button=findViewById(R.id.btnClick)
        editText=findViewById(R.id.etInput)
        button?.setOnClickListener {
            if (editText?.text.toString().isNullOrEmpty()){
                Toast.makeText(this,"Enter value first",Toast.LENGTH_SHORT).show()
            }else{
                var value=editText?.text.toString()
            var intent=Intent(this,RelativeActivity::class.java)
                .putExtra("data",value)
            startActivity(intent)
            Toast.makeText(this,value,Toast.LENGTH_SHORT).show()}
        }

        binding.btnAlertDialog.setOnClickListener {
            startActivity(Intent(this,AlertDialogActivity::class.java))
        }
        binding.btnImplicit.setOnClickListener {
            startActivity(Intent(this,ImplicitActivity::class.java))
        }
    }
}