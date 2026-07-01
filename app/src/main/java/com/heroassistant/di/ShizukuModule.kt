package com.heroassistant.di

import android.content.Context
import com.heroassistant.service.*
import com.heroassistant.util.ShizukuChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShizukuModule {

    @Provides
    @Singleton
    fun provideDeviceActor(
        standardActor: StandardActor,
        privilegedActor: PrivilegedActor,
        shizukuChecker: ShizukuChecker
    ): DeviceActor {
        return if (shizukuChecker.isShizukuAvailable()) {
            privilegedActor
        } else {
            standardActor
        }
    }

    @Provides
    @Singleton
    fun provideShizukuChecker(@ApplicationContext context: Context): ShizukuChecker {
        return ShizukuChecker(context)
    }

    @Provides
    @Singleton
    fun provideStandardActor(@ApplicationContext context: Context): StandardActor {
        return StandardActor(context)
    }

    @Provides
    @Singleton
    fun providePrivilegedActor(
        @ApplicationContext context: Context,
        shizukuUserService: ShizukuUserService
    ): PrivilegedActor {
        return PrivilegedActor(context, shizukuUserService)
    }

    @Provides
    @Singleton
    fun provideShizukuUserService(@ApplicationContext context: Context): ShizukuUserService {
        return ShizukuUserService(context)
    }
}