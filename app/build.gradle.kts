import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.o7solutions.androidkotlin5_6pmonline"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.o7solutions.androidkotlin5_6pmonline"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    val localProperties = Properties().apply {
        val file = rootProject.file("local.properties")
        if (file.exists()) {
            file.inputStream().use { load(it) }
        }
    }
    defaultConfig {
        val googleMapsKey = localProperties.getProperty("MY_SECRET_KEY") ?: "DUMMY_KEY"
        manifestPlaceholders["MY_SECRET_KEY"] = googleMapsKey
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures.viewBinding=true
    buildFeatures.dataBinding=true
    buildFeatures.buildConfig=true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.room:room-runtime:2.6.1")
//    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    //razorpay
    implementation ("com.razorpay:checkout:1.6.39")

//    fused location client
    implementation("com.google.android.gms:play-services-location:21.3.0")

    //Exo Player
    implementation ("androidx.media3:media3-exoplayer:1.3.1")
    implementation ("androidx.media3:media3-exoplayer-dash:1.3.1")
    implementation ("androidx.media3:media3-ui:1.3.1")

    //supabase storage
    implementation(platform("io.github.jan-tennert.supabase:bom:3.0.2"))
    implementation ("io.github.jan-tennert.supabase:storage-kt:3.0.2")

    //glide
    implementation ("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")

    //google maps
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:18.1.0")

}