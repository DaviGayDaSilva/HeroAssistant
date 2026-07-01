# Shizuku
-keep class dev.rikka.shizuku.** { *; }
-keep class moe.shizuku.** { *; }
-dontwarn dev.rikka.shizuku.**

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

# Room
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**

# Compose
-dontwarn androidx.compose.**
-keep class androidx.compose.** { *; }

# Keep data classes usadas em serialização (ex: JSON, Room)
-keepattributes *Annotation*, Signature, Exception
-keep class com.heroassistant.**.entity.** { *; }
-keep class com.heroassistant.**.model.** { *; }

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# WorkManager
-keep class androidx.work.impl.** { *; }