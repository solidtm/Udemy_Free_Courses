package com.solid.ufc.features.explore

import com.solid.ufc.data.model.explore.CourseListResponse

sealed class ExploreViewState {
    class Success(val message: Int, val courseListResponse: CourseListResponse? = null) : ExploreViewState()
    class Failure(val message: Int, val errorMessage: String = "") : ExploreViewState()
    class Loading(val message: Int? = null) : ExploreViewState()
    object Empty : ExploreViewState()
}