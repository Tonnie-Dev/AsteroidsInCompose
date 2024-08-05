buildscript {
    ext {
        compose_version = '1.1.1'
    }
    dependencies {
        classpath libs.hilt.android.gradle.plugin
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id 'com.android.application' apply false
    id 'com.android.library' apply false
    id 'org.jetbrains.kotlin.android' apply false
}

task clean(type: Delete) {
    delete rootProject.buildDir
}