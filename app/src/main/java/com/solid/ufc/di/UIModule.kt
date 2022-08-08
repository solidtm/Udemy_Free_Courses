package com.solid.ufc.di

import com.solid.ufc.util.ProgressLoader
import com.solid.ufc.util.ProgressLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface UIModule {

    @get:Binds
    val ProgressLoaderImpl.progressLoader: ProgressLoader
}