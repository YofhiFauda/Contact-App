plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-android")
    id ("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.yofhi.contactapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yofhi.contactapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0-alpha01")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0-rc01")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui:1.6.0-beta01")
    implementation("androidx.compose.ui:ui-graphics:1.6.0-beta01")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.0-beta01")
    implementation("androidx.compose.material3:material3:1.2.0-alpha11")

    //glide
    implementation ("com.github.bumptech.glide:glide:5.0.0-rc01")

    // Fragment
    implementation("androidx.fragment:fragment-ktx:1.7.0-alpha06")

    // Lifecycle + LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0-rc01")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.0-beta01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Navigation Component
    implementation ("androidx.navigation:navigation-compose:2.7.5")

    // Room
    implementation ("androidx.room:room-ktx:2.6.0")
    implementation ("androidx.room:room-runtime:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.48.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.48.1")

//    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    implementation ("androidx.hilt:hilt-navigation:1.1.0")
    implementation ("androidx.hilt:hilt-navigation-fragment:1.1.0")
    kapt ("androidx.hilt:hilt-compiler:1.1.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")

    implementation ("androidx.hilt:hilt-common:1.1.0")
    kapt ("com.google.dagger:hilt-android-compiler:2.48.1")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.0-beta01")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.0-beta01")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.0-beta01")

    hilt {
        enableAggregatingTask = true
    }
}