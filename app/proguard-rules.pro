# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# Enable shrinking, obfuscation, and optimization
-dontoptimize
-dontobfuscate

# Keep the classes and methods used by the Compose library
-keep class androidx.compose.** { *; }
-keep class androidx.activity.** { *; }
-keep class androidx.lifecycle.** { *; }
-keep class androidx.savedstate.** { *; }
-keep class androidx.compose.ui.** { *; }
-keep class androidx.compose.foundation.** { *; }
-keep class androidx.compose.material.** { *; }
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.animation.** { *; }
-keep class androidx.compose.material.icons.** { *; }
-keep class androidx.compose.material.icons.filled.** { *; }
-keep class androidx.compose.material.icons.outlined.** { *; }
-keep class androidx.compose.material.icons.rounded.** { *; }
-keep class androidx.compose.material.icons.sharp.** { *; }
-keep class androidx.compose.material.icons.twotone.** { *; }

# Keep the classes and methods used by Coil
-keep class coil.** { *; }
-keep class coil.request.** { *; }
-keep class coil.compose.** { *; }