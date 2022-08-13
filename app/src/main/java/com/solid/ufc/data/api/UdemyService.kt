package com.solid.ufc.data.api

import com.solid.ufc.data.model.explore.CourseListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UdemyService {

    @GET("courses/")
    suspend fun getCoursesList(
        @Query("page") state: Int? = null,
        @Query("page_size") status: Int? = null
    ): Response<CourseListResponse>
}