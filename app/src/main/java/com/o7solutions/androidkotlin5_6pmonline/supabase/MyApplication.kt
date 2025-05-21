package com.o7solutions.androidkotlin5_6pmonline.supabase

import android.app.Application
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.SupabaseClientBuilder
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage


class MyApplication:Application() {
    lateinit var supabaseClient: SupabaseClient
    override fun onCreate() {
        super.onCreate()
        supabaseClient = createSupabaseClient(
            "https://oqukjbibowanbifvxpqk.supabase.co",  // Your Supabase URL
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9xdWtqYmlib3dhbmJpZnZ4cHFrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE1NzcxMjcsImV4cCI6MjA0NzE1MzEyN30.oZk4pbB31euKfH_Bk2aI175f7eUR8HmrJn9nOanbSis"
        ) {
            install(Storage)
            // Additional configuration can be added here if needed
        }

        // Now you can access Supabase Auth and Storage through the `supabaseClient`
//        val auth = supabaseClient.auth
        val bucket = supabaseClient.storage.from("new_bucket")

    }
}