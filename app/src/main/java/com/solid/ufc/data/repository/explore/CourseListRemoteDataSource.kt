package com.solid.ufc.data.repository.explore

import com.solid.ufc.core.functional.Result


interface CourseListRemoteDataSource {
    suspend fun getCourses(page: Int, pageSize: Int): Result<*>
}