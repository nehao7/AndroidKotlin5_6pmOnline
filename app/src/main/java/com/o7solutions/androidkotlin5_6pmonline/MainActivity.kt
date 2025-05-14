package com.o7solutions.androidkotlin5_6pmonline

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7solutions.androidkotlin5_6pmonline.databinding.ActivityMainBinding
import com.o7solutions.androidkotlin5_6pmonline.firebase.FirestoreActivity
import com.o7solutions.androidkotlin5_6pmonline.firebase.RegisterActivity
import com.o7solutions.androidkotlin5_6pmonline.fragment_activity_interaction.BaseActivity
import com.o7solutions.androidkotlin5_6pmonline.jetpacknav.NavControllerActivity
import com.o7solutions.androidkotlin5_6pmonline.listpackage.ListBaseAdapterActivity
import com.o7solutions.androidkotlin5_6pmonline.location.LocationActivity
import com.o7solutions.androidkotlin5_6pmonline.realtimedatabase.RealtimeActivity
import com.o7solutions.androidkotlin5_6pmonline.recycler_package.RecyclerActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var button: Button? = null
    var editText: EditText? = null
    lateinit var myMenu: Unit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Home Screen"
        button = findViewById(R.id.btnClick)
        editText = findViewById(R.id.etInput)
        button?.setOnClickListener {
            if (editText?.text.toString().isNullOrEmpty()) {
                Toast.makeText(this, "Enter value first", Toast.LENGTH_SHORT).show()
            } else {
                var value = editText?.text.toString()
                var intent = Intent(this, RelativeActivity::class.java)
                    .putExtra("data", value)
                startActivity(intent)
                Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAlertDialog.setOnClickListener {
            startActivity(Intent(this, AlertDialogActivity::class.java))
        }
        binding.btnImplicit.setOnClickListener {
            startActivity(Intent(this, ImplicitActivity::class.java))
        }
        binding.btnJetPack.setOnClickListener {
            startActivity(Intent(this, NavControllerActivity::class.java))
        }
        binding.btnSpinner.setOnClickListener {
            startActivity(Intent(this, SpinnerActivity::class.java))
        }
        binding.btnList.setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))
        }
        binding.btnList.setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))
        }
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }
        binding.btnListView.setOnClickListener {
            startActivity(Intent(this, ListBaseAdapterActivity::class.java))
        }
        binding.btnRecyclerView.setOnClickListener {
            startActivity(Intent(this, RecyclerActivity::class.java))
        }
        binding.btnInteraction.setOnClickListener {
            startActivity(Intent(this, BaseActivity::class.java))
        }
        binding.btnFirebaseReg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.btnRealtimeDatabase.setOnClickListener {
            startActivity(Intent(this, RealtimeActivity::class.java))
        }
        binding.btnFirestoreDatabase.setOnClickListener {
            startActivity(Intent(this, FirestoreActivity::class.java))
        }

        binding.btnLocationActivity.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        myMenu = menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.mnProfile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                return true
            }

            R.id.mnSetting -> {

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }


    }
}