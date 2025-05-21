package com.o7solutions.androidkotlin5_6pmonline.supabase

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.o7services.androidkotlin2to4pm.R
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.UploadStatus
import io.github.jan.supabase.storage.storage
import io.github.jan.supabase.storage.uploadAsFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SupabaseActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    private val PERMISSION_REQUEST_CODE = 100
    private val MANAGE_EXTERNAL_STORAGE_REQUEST_CODE = 101
    private lateinit var supabaseClient: SupabaseClient
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_supabase)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supabaseClient = (applicationContext as MyApplication).supabaseClient
        checkAndRequestPermissions()
        findViewById<Button>(R.id.uploadButton).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // For Android 10 (API level 29) and above, request MANAGE_EXTERNAL_STORAGE if needed
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // For Android 11 (API level 30) and above, check for external storage manager permission
                if (Environment.isExternalStorageManager()) {
                    // Permission granted, proceed
                } else {
                    // Ask for permission
                    requestManageExternalStoragePermission()
                }
            } else {
                // For Android 10 (API level 29), request MANAGE_EXTERNAL_STORAGE if needed
                if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Request permission
                    requestManageExternalStoragePermission()
                }
            }
        } else {
            // For devices below Android 10, we need the read permission
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                // Request permission
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            }
        }


    }
    private fun requestManageExternalStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For Android 11 (API level 30) and above
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                startActivityForResult(intent, PERMISSION_REQUEST_CODE)
            } catch (e: ActivityNotFoundException) {
                // Handle the case where the intent is not available (maybe the device is on a lower version)
                Log.e("PermissionRequest", "Activity not found for the permission intent.")
            }
        } else {
            // Handle this case for older Android versions (below Android 11)
            Log.e("PermissionRequest", "The permission is only available on Android 11 (API level 30) and above.")
        }
//        // Request the user to open settings to allow full access
//        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
//        startActivityForResult(intent, MANAGE_EXTERNAL_STORAGE_REQUEST_CODE)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, proceed with file operations
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            MANAGE_EXTERNAL_STORAGE_REQUEST_CODE -> {
                if (Environment.isExternalStorageManager()) {
                    // The user granted permission for full access
                    Toast.makeText(this, "Full storage access granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Storage permission not granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            data?.data?.let { uri ->
                // Handle the selected image (upload it to Supabase)
                uploadImageToSupabase(uri)
            }
        }
    }


    private fun uploadImageToSupabase(uri: Uri) {
        val byteArray = uriToByteArray(this, uri)
        val fileName = "uploads/${System.currentTimeMillis()}.jpg"

        val bucket = supabaseClient.storage.from("new_bucket") // Choose your bucket name

        // Use lifecycleScope for safe coroutine usage
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Upload image and handle the response
                bucket.uploadAsFlow(fileName, byteArray).collect { status ->
                    withContext(Dispatchers.Main) {
                        when (status) {
                            is UploadStatus.Progress -> {
//                                val progress = (status.totalBytesSent.toFloat() / status.contentLength * 100)
                                Log.d("Upload", "Progress%")
                            }
                            is UploadStatus.Success -> {
                                Log.d("Upload ", "Upload Success")
                                val imageUrl = bucket.publicUrl(fileName)
                                var img: ImageView =findViewById(R.id.img)

                                Glide.with(this@SupabaseActivity)
                                    .load(imageUrl)
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .into(img)

                                Toast.makeText(this@SupabaseActivity, "Upload Successful!", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("Upload", "Error uploading image: ${e.message}")
                    Toast.makeText(this@SupabaseActivity, "Error uploading image: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }



    private fun uriToByteArray(context: Context, uri: Uri): ByteArray {
        val inputStream = context.contentResolver.openInputStream(uri)
        return inputStream?.readBytes() ?: ByteArray(0)
    }

}