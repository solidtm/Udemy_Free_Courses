package com.solid.ufc.data.repository.explore

import com.solid.ufc.core.functional.Failure
import com.solid.ufc.data.api.UdemyService
import com.solid.ufc.core.functional.Result
import com.solid.ufc.util.handleErrorMessage
import javax.inject.Inject

class CourseListRemoteDataSourceImpl @Inject constructor(
    private val udemyService : UdemyService
    ): CourseListRemoteDataSource {

    override suspend fun getCourses(page: Int, pageSize: Int): Result<*> {
        return try{
            val res = udemyService.getCoursesList(page, pageSize)

            when(res.isSuccessful){
                true -> {
                    res.body()?.let {
                        Result.Success(it)
                    } ?: Result.Error(Failure.ServerError)
                }
                false ->{
                    val errorMessage = handleErrorMessage(res.errorBody()!!)
                    Result.Failed(errorMessage)
                }
            }
        }catch (e: Throwable){
            Result.Failed(e.message)
        }
    }
}