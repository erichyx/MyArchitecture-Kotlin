# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
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
#################### okhttp3 ####################
-dontwarn okhttp3.**

#################### okio ####################
-dontwarn okio.**

#################### retrofit2 ####################
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

#################### glide ####################
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#################### navigation中的Fragment ####################
-keep class cn.eric.arch.mvp.MvpFragment
-keep class cn.eric.arch.mvvm.MovieFragment
-keep class cn.eric.arch.OtherFragment

#################### 实体类 ####################
-keepclassmembers class cn.eric.arch.data.local.*Entity { *; }
-keepclassmembers class cn.eric.arch.data.local.*Bean { *; }
#-keepclassmembers class com.arch.eric.data.local.MovieSubjectEntity$* { *; }
-keepclassmembers class cn.eric.arch.entity.* { *; }