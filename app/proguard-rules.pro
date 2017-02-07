# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Users\dell\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-target 1.6
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-dontwarn
-dump class_files.txt
-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes *JavascriptInterface*
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

-dontwarn  com.google.**
-dontwarn org.apache.velocity.**
-dontwarn org.apache.commons.**
-dontwarn com.slidingmenu.**
-dontwarn com.lidroid.xutils.**
-dontwarn com.lidroid.xutils.http.**
-dontwarn org.android.**

-keepclasseswithmembernames class * {
    native <methods>;
}

-dontwarn com.ut.mini.**
-dontwarn com.google.common.**
-dontwarn  u.aly.bt

-keep class com.xiong.dandan.wudd.net.response.**{*;}
-keep class com.xiong.dandan.wudd.common.entiy.**{*;}