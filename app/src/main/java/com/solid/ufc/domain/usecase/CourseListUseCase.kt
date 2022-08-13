package com.solid.ufc.domain.usecase

import com.solid.ufc.core.base.FlowUseCase
import com.solid.ufc.core.functional.Result
import com.solid.ufc.data.model.explore.CourseListRequest
import com.solid.ufc.domain.repository.CourseListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CourseListUseCase @Inject constructor(
    private val courseListRepository: CourseListRepository)
    : FlowUseCase<CourseListRequest, Any> {


    override suspend fun invoke(params: CourseListRequest?): Flow<Result<Any>> {
        return courseListRepository.getCourses(params?.page!!, params.pageSize)
    }

}