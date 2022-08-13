package com.solid.ufc.di

import com.solid.ufc.BuildConfig
import com.solid.ufc.data.api.TokenInterceptor
import com.solid.ufc.data.api.UdemyService
import com.solid.ufc.data.api.UdemyServiceFactory
import com.solid.ufc.data.repository.explore.CourseListRemoteDataSource
import com.solid.ufc.data.repository.explore.CourseListRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {

    @get:Binds
    val CourseListRemoteDataSourceImpl.courseListRemoteImpl: CourseListRemoteDataSource   //binding the login remote to its impl

    companion object {
        @[Provides Singleton]
        fun provideApiService(tokenInterceptor: TokenInterceptor): UdemyService =
            UdemyServiceFactory.createApiService(BuildConfig.DEBUG, tokenInterceptor)
    }
}