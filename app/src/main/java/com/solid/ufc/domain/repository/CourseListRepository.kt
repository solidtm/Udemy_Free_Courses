package com.solid.ufc.domain.repository

import com.solid.ufc.core.functional.Result
import kotlinx.coroutines.flow.Flow

interface CourseListRepository {
    fun getCourses(page: Int, pageSize: Int) : Flow<Result<Any>>
}