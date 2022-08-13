package com.solid.ufc.di

import com.solid.ufc.data.CourseListRepositoryImpl
import com.solid.ufc.domain.repository.CourseListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @get:Binds
    val CourseListRepositoryImpl.courseListRepoImpl: CourseListRepository
}