package com.solid.ufc.core.base

import com.solid.ufc.core.functional.Result
import kotlinx.coroutines.flow.Flow

interface FlowUseCase<in Params, out Type> {
    suspend operator fun invoke(params: Params? = null): Flow<Result<Type>>
}