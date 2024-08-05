plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
    id 'kotlin-parcelize'
    id 'com.google.devtools.ksp' version '1.6.10-1.0.2'
}

android {
    compileSdk 33

    defaultConfig {
        applicationId "com.uxstate"
        minSdk 21
        targetSdk 33
        versionCode 2
        versionName "1.1"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary true
        }
    }

    //Kotlin Block - makes sure that the KSP Plugin looks at
// the right paths when it comes to generated classes

    kotlin {
        sourceSets {
            debug {
                kotlin.srcDir("build/generated/ksp/debug/kotlin")
            }
            release {
                kotlin.srcDir("build/generated/ksp/release/kotlin")
            }
        }
    }


    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        coreLibraryDesugaringEnabled true
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion compose_version
    }
    packagingOptions {
        resources {
            excludes += '/META-INF/{AL2.0,LGPL2.1}'
        }
    }
    namespace 'com.uxstate'
}

dependencies {

    implementation libs.core.ktx
    implementation libs.ui

    implementation libs.ui.tooling.preview
    implementation libs.lifecycle.runtime.ktx
    implementation libs.activity.compose

    testImplementation libs.junit.junit
    androidTestImplementation libs.androidx.test.ext.junit
    androidTestImplementation libs.espresso.core
    androidTestImplementation libs.ui.test.junit4
    debugImplementation libs.ui.tooling
    debugImplementation libs.ui.test.manifest
    coreLibraryDesugaring libs.desugar.jdk.libs

    // Material 3

    //implementation libs.material3
    implementation libs.material3
    // Compose dependencies
    implementation libs.lifecycle.viewmodel.compose
    implementation libs.material.icons.extended
    implementation libs.activity.compose
    implementation libs.accompanist.swiperefresh
    implementation libs.animation.graphics
    implementation libs.animation.core

    // Compose Nav Destinations
    implementation libs.io.github.raamcosta.compose.destinations.core
    ksp libs.ksp

    // Coil
    implementation libs.coil.compose

    //Dagger - Hilt
    implementation libs.hilt.android
    kapt libs.hilt.android.compiler
   // implementation "androidx.hilt:hilt-lifecycle-viewmodel:_"
    kapt libs.hilt.compiler
    implementation libs.hilt.navigation.compose

    // Retrofit
    implementation libs.retrofit
    implementation libs.converter.moshi
    implementation libs.okhttp
    implementation libs.logging.interceptor

    //Moshi Library Dependencies - Core Moshi JSON Library and Moshi's Kotlin support and converter factory
    implementation libs.moshi
    implementation libs.moshi.kotlin

    // Room
    implementation libs.room.runtime
    kapt libs.room.compiler

    // Kotlin Extensions and Coroutines support for Room
    implementation libs.room.ktx

    //Timber
    implementation libs.timber


    //Flow Layout
    implementation libs.accompanist.flowlayout

    //Lottie Animation
    implementation libs.lottie.compose


}