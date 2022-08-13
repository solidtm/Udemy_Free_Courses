package com.solid.ufc.data

import com.solid.ufc.core.functional.Result
import com.solid.ufc.data.model.explore.CourseListResponse
import com.solid.ufc.data.repository.explore.CourseListRemoteDataSource
import com.solid.ufc.domain.repository.CourseListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CourseListRepositoryImpl @Inject constructor(
    private val courseListRemoteDataSource: CourseListRemoteDataSource
) : CourseListRepository {

    override fun getCourses(page: Int, pageSize: Int): Flow<Result<Any>> {
        return flow {
            when (val res = courseListRemoteDataSource.getCourses(page, pageSize)) {
                is Result.Success<*> -> {
                    if (res.data is CourseListResponse) {
                        emit(Result.Success(res.data))
                    }
                }
                is Result.Failed<*> -> {
                    emit(Result.Failed(res.errorMessage!!))
                }
                else -> {}
            }
        }
    }

//    override fun getCourses(page: Int, pageSize: Int): Flow<Result<Any>> {
//        return flow {
//            when (val res = courseListRemoteDataSource.getCourses(page, pageSize)) {
//                is Result.Success<*> -> {
//                    if (res.data is CourseListResponse) {
//                        emit(Result.Success(res.data))
//                    }
//                }
//                is Result.Failed<*> -> {
//                    emit(Result.Failed(res.errorMessage!!))
//                }
//                else -> {}
//            }
//        }
//    }
}