package com.o7solutions.androidkotlin5_6pmonline

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityAlertDialogBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class AlertDialogActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlertDialogBinding
    var itemsList= arrayOf("Item 1","Item 2","Item 3","Item 4")
    var boolArray= booleanArrayOf(true, false , true , true)
    var formatter=SimpleDateFormat("dd-mmm-yyyy")
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

        binding.btnSnackbar.setOnClickListener {
            Snackbar.make(it, "This is a simple Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Undo", {
                    Toast.makeText(
                        this, "Toast is invoked from snackbar",
                        Toast.LENGTH_SHORT
                    ).show()
                }).show()
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
//            alertdialog.setMessage("This is my AlertDialog")
            alertdialog.setItems(itemsList){dialog,index->
                Toast.makeText(this,itemsList[index], Toast.LENGTH_SHORT).show()

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
        binding.btnMutipleChoiceAD.setOnClickListener {
            AlertDialog.Builder(this).apply {
            setTitle("Alert Dialog")
                setMultiChoiceItems(itemsList,boolArray){_,index,boolvalue->
                    boolArray.set(index,boolvalue)
                    Toast.makeText(this@AlertDialogActivity,itemsList[index], Toast.LENGTH_SHORT).show()


                }
            setNegativeButton("No"){_,_->
                Toast.makeText(this@AlertDialogActivity, "Negative Button Clicked", Toast.LENGTH_SHORT).show()
            }
            setPositiveButton("Yes"){_,_->
                Toast.makeText(this@AlertDialogActivity, "Positive Button Clicked", Toast.LENGTH_SHORT).show()
            }
           setNeutralButton("Cancel"){_,_->
                Toast.makeText(this@AlertDialogActivity, "Neutral Button Clicked", Toast.LENGTH_SHORT).show()
            }
            setCancelable(false)
            show()
        }
        }
        binding.btnCustomDalog.setOnClickListener {
            var dialog=Dialog(this)
            dialog.setContentView(R.layout.custom_dialog_layout)
            dialog.show()
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        binding.btnDatePicker.setOnClickListener {
            DatePickerDialog(this,
                {_,year,month ,dayofmonth->
                    var calendar=Calendar.getInstance()
                    calendar.set(year,month,dayofmonth)
                    var formattedDate=formatter.format(calendar.time)
                    binding.btnDatePicker.setText(formattedDate.toString())
            },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                ).show()
        }
    }
}
