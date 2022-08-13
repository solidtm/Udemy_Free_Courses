package com.solid.ufc.features.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solid.ufc.R
import com.solid.ufc.core.functional.Result
import com.solid.ufc.data.api.TokenInterceptor
import com.solid.ufc.data.model.ErrorMessage
import com.solid.ufc.data.model.explore.CourseListRequest
import com.solid.ufc.data.model.explore.CourseListResponse
import com.solid.ufc.domain.usecase.CourseListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val courseListUseCase: CourseListUseCase
    ) : ViewModel() {

    private val _courseListChannel = Channel<ExploreViewState>()
    val courseListChannel = _courseListChannel.receiveAsFlow()

    fun getCourses(courseListRequest: CourseListRequest) {
        viewModelScope.launch {
            _courseListChannel.send(ExploreViewState.Loading(R.string.sign_in))

            courseListUseCase.invoke(courseListRequest).collect {

                when (it) {
                    is Result.Success -> {
                        if (it.data is CourseListResponse) {
                            _courseListChannel.send(
                                ExploreViewState.Success(
                                    R.string.find_a_course,
                                    it.data
                                )
                            )
                        }
                    }
                    is Result.Failed-> {
                        if (it.errorMessage is ErrorMessage) {
                            _courseListChannel.send(
                                ExploreViewState.Failure(
                                    R.string.find_a_course,
                                    it.errorMessage.message
                                )
                            )
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}