package com.o7solutions.androidkotlin5_6pmonline

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityAlertDialogBinding

class AlertDialogActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlertDialogBinding
    var itemsList= arrayOf("Item 1","Item 2","Item 3","Item 4")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityAlertDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBasicAD.setOnClickListener {
            var alertdialog=AlertDialog.Builder(this)
            alertdialog.setTitle("Alert Dialog")
            alertdialog.setMessage("This is my AlertDialog")
            alertdialog.setNegativeButton("No"){_,_->
                Toast.makeText(this, "Negative Button Clicked", Toast.LENGTH_SHORT).show()
            }
            alertdialog.setPositiveButton("Yes"){_,_->
                Toast.makeText(this, "Positive Button Clicked", Toast.LENGTH_SHORT).show()

            }
            alertdialog.setNeutralButton("Cancel"){_,_->
                Toast.makeText(this, "Neutral Button Clicked", Toast.LENGTH_SHORT).show()
            }
            alertdialog.setCancelable(false)
            alertdialog.show()
        }

        binding.btnSingleChoiceAD.setOnClickListener {
            var alertdialog=AlertDialog.Builder(this)
            alertdialog.setTitle("Alert Dialog")
            alertdialog.setMessage("This is my AlertDialog")
            alertdialog.setItems(itemsList){dialog,index->
                
            }
            alertdialog.setNegativeButton("No"){_,_->
                Toast.makeText(this, "Negative Button Clicked", Toast.LENGTH_SHORT).show()
            }
            alertdialog.setPositiveButton("Yes"){_,_->
                Toast.makeText(this, "Positive Button Clicked", Toast.LENGTH_SHORT).show()
            }
            alertdialog.setNeutralButton("Cancel"){_,_->
                Toast.makeText(this, "Neutral Button Clicked", Toast.LENGTH_SHORT).show()
            }
            alertdialog.setCancelable(false)
            alertdialog.show()
        }
    }
}