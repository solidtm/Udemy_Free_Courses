package com.solid.ufc.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.solid.ufc.data.model.ErrorMessage
import okhttp3.ResponseBody

fun handleErrorMessage(res: ResponseBody): ErrorMessage {

    var errorMessage = ErrorMessage()
    val gson = Gson()
    val type = object : TypeToken<ErrorMessage>() {}.type
    val errorResponse: ErrorMessage? = gson.fromJson(res.charStream(), type)
    if (errorResponse != null) {
        errorMessage = errorResponse
    }
    return errorMessage
}