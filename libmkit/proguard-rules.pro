# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AndroidSDK/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#  指定代码的压缩级别
  -optimizationpasses 10
  #混淆时不使用大小写混合类名
  -dontusemixedcaseclassnames
  #不跳过library中的非public的类
  -dontskipnonpubliclibraryclasses
   #表示不进行优化，建议使用此选项，因为根据proguard-android-optimize.txt中的描述，
   #优化可能会造成一些潜在风险，不能保证在所有版本的Dalvik上都正常运行
  -dontoptimize
   #表示不进行预校验。这个预校验是作用在Java平台上的，Android平台上不需要这项功能，去掉之后还可以加快混淆速度
  -dontpreverify
   #混淆时是否记录日志
  -verbose
   #表示对注解中的参数进行保留
  -keepattributes *Annotation*
   # 混淆时所采用的算法
  -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
   #表示不混淆任何包含native方法的类的类名以及native方法名
  -keepclasseswithmembernames class * {
      native <methods>;
  }
#表示不混淆枚举中的values()和valueOf()方法
  -keepclassmembers enum * {
      public static **[] values();
      public static ** valueOf(java.lang.String);
  }
    #不混淆资源类,文件中的所有静态字段
    -keepclassmembers class **.R$* {
        public static <fields>;
    }
    #避免混淆泛型 如果混淆报错建议关掉
    -keepattributes Signature



  #保持自定义控件类不被混淆
  -keepclasseswithmembers class * {
      public <init>(android.content.Context, android.util.AttributeSet);
  }
  #保持自定义控件类不被混淆
  -keepclasseswithmembers class * {
      public <init>(android.content.Context, android.util.AttributeSet, int);
  }

  -keep public class * extends android.app.Fragment
  -keep public class * extends android.app.Activity


    -dontwarn org.codehaus.**
    -dontwarn java.nio.**
    -dontwarn java.lang.invoke.**
    -dontwarn rx.internal.util.unsafe.*


    # okhttp
    -dontwarn okio.**


     -dontnote retrofit2.Platform
     -dontnote retrofit2.Platform$IOS$MainThreadExecutor
     -dontwarn retrofit2.Platform$Java8

    #retrofit2.x
    -dontwarn retrofit2.**
    -keep class retrofit2.** { *; }
    -keepattributes Signature
    -keepattributes Exceptions


    #webView处理，项目中没有使用到webView忽略即可
    -keepclassmembers class fqcn.of.javascript.interface.for.webview {
       public *;
    }
    -keepclassmembers class * extends android.webkit.webViewClient {
        public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
        public boolean *(android.webkit.WebView, java.lang.String);
    }
    -keepclassmembers class * extends android.webkit.webViewClient {
        public void *(android.webkit.webView, jav.lang.String);
    }

    ###Jsoup
    -keep public class org.jsoup.** {
    public *;
    }

 #如果引用了v4或者v7包
  -dontwarn android.support.**
    #如果有引用v4包可以添加下面这行
    -keep public class * extends android.support.v4.app.Fragment

    #-keep class **{*;}
    -keepclassmembers class * extends android.support.v4.app.FragmentActivity{
        public void *(android.view.View);
    }

    ###Glide
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public class * extends com.bumptech.glide.AppGlideModule
    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }
    -dontwarn com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
    -dontwarn com.bumptech.glide.load.resource.bitmap.Downsampler
    -dontwarn com.bumptech.glide.load.resource.bitmap.HardwareConfigState

#实体类
#-----------------------------------------------------------------------------
-keep class com.mkit.libmkit.bean.** {*;}
-keep class com.mkit.libmkit.api.** {*;}
-keep class com.mkit.libmkit.ui.** {*;}